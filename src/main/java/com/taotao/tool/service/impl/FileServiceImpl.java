package com.taotao.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taotao.tool.dto.FileExt;
import com.taotao.tool.dto.req.UploadFileReq;
import com.taotao.tool.enums.EApiCode;
import com.taotao.tool.enums.EFileType;
import com.taotao.tool.exception.ApiException;
import com.taotao.tool.mapper.FileMapper;
import com.taotao.tool.model.File;
import com.taotao.tool.service.IFileService;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.util.JsonUtils;
import com.taotao.tool.yml.ImageYml;
import com.taotao.tool.yml.UploadYml;
import com.taotao.tool.yml.VideoYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Override
    public List<File> doUploadFile(List<UploadFileReq.FileItem> fileItemList) throws IOException {
        List<File> fileList = Lists.newArrayList();
        for (UploadFileReq.FileItem fileItem : fileItemList) {
            MultipartFile multipartFile = fileItem.getFile();
            String contentType = multipartFile.getContentType();
            ApiAssertUtils.notNull(contentType, "文件类型不能为空");
            String md5 = DigestUtils.md5DigestAsHex(multipartFile.getInputStream());
            File originFile = getByMd5(md5);
            if (Objects.nonNull(originFile)) {
                log.info("act=doUploadFile type=alreadyExist filename={}", originFile.getFilename());
                fileList.add(originFile);
                continue;
            }
            String[] types = contentType.split("/");
            EFileType fileType = EFileType.valueOf(types[0]);
            String fileTypeDetail = types[1];
            // 保存到磁盘
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + fileTypeDetail;
            String diskPath = getUploadDiskPath(fileType);
            java.io.File newFile = new java.io.File(diskPath, newFilename);
            multipartFile.transferTo(newFile);
            // 记录至 file 表
            File addFile = new File();
            addFile.setFilename(newFilename);
            addFile.setMd5(md5);
            addFile.setBytes(newFile.length());
            addFile.setFileType(fileType);
            addFile.setFileTypeDetail(fileTypeDetail);
            String ext = parseFileExt(fileType, fileItem.getFileExt());
            addFile.setExt(ext);
            addFile.setGmtCreate(LocalDateTime.now());
            addFile.setGmtModified(LocalDateTime.now());
            fileList.add(addFile);
        }
        List<File> addFileList = fileList.stream().filter(item -> Objects.isNull(item.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addFileList)) {
            boolean saveResp = saveBatch(addFileList);
            log.info("act=doUploadFile type=saveBatch saveSize={} saveResp={}", addFileList.size(), saveResp);
        }
        return fileList;
    }

    private String parseFileExt(EFileType fileType, UploadFileReq.FileItemExt fileExt) throws IOException {
        if (Objects.equals(EFileType.video, fileType) && Objects.nonNull(fileExt)) {
            // 先上传视频封面
            UploadFileReq.FileItem uploadCoverFileItem = new UploadFileReq.FileItem();
            uploadCoverFileItem.setFile(fileExt.getCoverFile());
            File coverFile = doUploadFile(Lists.newArrayList(uploadCoverFileItem)).get(0);
            FileExt ext = new FileExt(coverFile.getFilename(), fileExt.getSecond());
            return JsonUtils.toJson(ext);
        }
        return null;
    }

    private String getUploadDiskPath(EFileType fileType) {
        ImageYml imageYml = uploadYml.getImage();
        VideoYml videoYml = uploadYml.getVideo();
        switch (fileType) {
            case image:
                return imageYml.getPath();
            case video:
                return videoYml.getPath();
            default:
                throw new ApiException(EApiCode.UNKNOWN, "暂不支持的文件类型");
        }
    }

    @Override
    public File getByMd5(String md5) {
        return query().eq("md5", md5).one();
    }
}
