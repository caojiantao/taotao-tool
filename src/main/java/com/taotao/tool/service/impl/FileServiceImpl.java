package com.taotao.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taotao.tool.dto.FFmpegFrameMultiPartFile;
import com.taotao.tool.dto.FileExt;
import com.taotao.tool.enums.EFileType;
import com.taotao.tool.event.upload.Event;
import com.taotao.tool.mapper.FileMapper;
import com.taotao.tool.model.File;
import com.taotao.tool.model.User;
import com.taotao.tool.service.IFileService;
import com.taotao.tool.util.*;
import com.taotao.tool.yml.ThumbYml;
import com.taotao.tool.yml.UploadYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2022-09-30
 */
@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Autowired
    private UploadYml uploadYml;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<File> doBatchUpload(List<MultipartFile> files) throws IOException {
        List<File> fileList = Lists.newArrayList();
        for (MultipartFile multipartFile : files) {
            String contentType = multipartFile.getContentType();
            ApiAssertUtils.notNull(contentType, "文件类型不能为空");
            String md5 = DigestUtils.md5DigestAsHex(multipartFile.getBytes());
            File originFile = getByMd5(md5);
            if (Objects.nonNull(originFile)) {
                log.info("act=doBatchUpload type=alreadyExist filename={}", originFile.getFilename());
                fileList.add(originFile);
                continue;
            }
            String[] types = contentType.split("/");
            EFileType fileType = EFileType.valueOf(types[0]);
            // org.springframework.boot.web.server.MimeMappings
            String originalFilename = multipartFile.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + extension;
            // 保存到磁盘（源文件）
            String diskPath = getUploadDiskPath(fileType.name());
            java.io.File newFile = new java.io.File(diskPath, newFilename);
            multipartFile.transferTo(newFile);
            // 保存到磁盘（缩略图）
            if (EFileType.image.equals(fileType)) {
                ThumbYml thumb = uploadYml.getThumb();
                String thumbDir = getUploadDiskPath(thumb.getPath());
                java.io.File thumbFile = new java.io.File(thumbDir, newFilename);
                ThumbnailUtils.compress(newFile, thumbFile, thumb.getQuality());
            }
            // 记录至 file 表
            File addFile = new File();
            Integer userId = Optional.ofNullable(LoginUtils.getCurrentUser()).map(User::getId).orElse(-1);
            addFile.setUserId(userId);
            addFile.setFilename(newFilename);
            addFile.setMd5(md5);
            addFile.setBytes(newFile.length());
            addFile.setFileType(fileType);
            addFile.setMimeType(contentType);
            addFile.setGmtCreate(LocalDateTime.now());
            addFile.setGmtModified(LocalDateTime.now());
            fileList.add(addFile);
        }
        List<File> addFileList = fileList.stream().filter(item -> Objects.isNull(item.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addFileList)) {
            boolean saveResp = saveBatch(addFileList);
            // 通过 event 机制，异步解析文件 ext 信息
            addFileList.forEach(item -> eventPublisher.publishEvent(new Event(item.getId())));
            log.info("act=doBatchUpload type=saveBatch saveSize={} saveResp={}", addFileList.size(), saveResp);
        }
        return fileList;
    }

    @Override
    public String parseFileExt(Integer fileId) throws IOException {
        log.info("act=parseFileExt fileId={}", fileId);
        File file = getById(fileId);
        EFileType fileType = file.getFileType();
        String diskPath = getUploadDiskPath(fileType.name());
        java.io.File newFile = new java.io.File(diskPath, file.getFilename());
        if (Objects.equals(EFileType.video, fileType)) {
            // 视频封面、时长
            FileExt ext = new FileExt();
            FFmpegUtils.parseVideo(newFile, grabber -> {
                long second = grabber.getLengthInTime() / 1000000;
                ext.setSecond(second);
                try {
                    FFmpegFrameMultiPartFile multiPartFile = new FFmpegFrameMultiPartFile(grabber);
                    String newFilename = doBatchUpload(Lists.newArrayList(multiPartFile)).get(0).getFilename();
                    ext.setCoverFilename(newFilename);
                    log.info("act=parseFileExt fileId={} ext={}", fileId, JsonUtils.toJson(ext));
                } catch (Exception e) {
                    log.error("act=parseFileExt fileId={}", fileId, e);
                }
            });
            return JsonUtils.toJson(ext);
        }
        return null;
    }

    private String getUploadDiskPath(String path) {
        String rootPath = System.getProperty("user.dir");
        return rootPath + "/static/" + path;
    }

    @Override
    public File getByMd5(String md5) {
        return query().eq("md5", md5).one();
    }
}
