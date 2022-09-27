package com.taotao.tool.service.facade;

import com.google.common.collect.Lists;
import com.taotao.tool.dto.resp.FileResp;
import com.taotao.tool.enums.EApiCode;
import com.taotao.tool.enums.EFileType;
import com.taotao.tool.exception.ApiException;
import com.taotao.tool.model.Pic;
import com.taotao.tool.model.Video;
import com.taotao.tool.service.IPicService;
import com.taotao.tool.service.IVideoService;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.yml.PicYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author caojiantao
 */
@Slf4j
@Component
public class FileFacade {

    @Autowired
    private PicYml picYml;
    @Autowired
    private IPicService picService;
    @Autowired
    private IVideoService videoService;

    public List<FileResp> doUpload(List<MultipartFile> files) throws Exception {
        ApiAssertUtils.isTrue(files.size() <= 20, "上传文件数量不能超过 20");
        String picDiskPath = picYml.getPath();
        List<FileResp> fileRespList = Lists.newArrayList();
        List<Pic> picList = Lists.newArrayList();
        List<Video> videoList = Lists.newArrayList();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            ApiAssertUtils.notNull(contentType, "文件类型不能为空");
            String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            Pic originPic = picService.getByMd5(md5);
            if (Objects.nonNull(originPic)) {
                log.info("act=batchUploadFile type=alreadyExist fileType=picture filename={}", filename);
                FileResp fileResp = FileResp.builder().fileType(EFileType.IMAGE).pic(originPic).build();
                fileRespList.add(fileResp);
                continue;
            }
            Video originVideo = videoService.getByMd5(md5);
            if (Objects.nonNull(originVideo)) {
                log.info("act=batchUploadFile type=alreadyExist fileType=video filename={}", filename);
                FileResp fileResp = FileResp.builder().fileType(EFileType.VIDEO).video(originVideo).build();
                fileRespList.add(fileResp);
                continue;
            }
            String ext = contentType.substring(contentType.lastIndexOf("/") + 1);
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + ext;
            File newFile = new File(picDiskPath, newFilename);
            file.transferTo(newFile);
            log.info("act=batchUploadPic type=saveDisk filename={} newFilename={}", filename, newFilename);
            if (contentType.startsWith("image")) {
                Pic pic = new Pic();
                pic.setFilename(newFilename);
                pic.setMd5(md5);
                pic.setBytes(newFile.length());
                pic.setContentType(contentType);
                pic.setGmtCreate(LocalDateTime.now());
                pic.setGmtModified(LocalDateTime.now());
                picList.add(pic);
                FileResp fileResp = FileResp.builder().fileType(EFileType.IMAGE).pic(pic).build();
                fileRespList.add(fileResp);
            } else if (contentType.startsWith("video")) {
                Video video = new Video();
                video.setFilename(newFilename);
                video.setMd5(md5);
                video.setBytes(newFile.length());
                video.setContentType(contentType);
                video.setGmtCreate(LocalDateTime.now());
                video.setGmtModified(LocalDateTime.now());
                videoList.add(video);
                FileResp fileResp = FileResp.builder().fileType(EFileType.VIDEO).video(video).build();
                fileRespList.add(fileResp);
            } else {
                throw new ApiException(EApiCode.UNKNOWN, "暂不支持的文件类型");
            }
        }
        if (!CollectionUtils.isEmpty(picList)) {
            picService.saveBatch(picList);
        }
        if (!CollectionUtils.isEmpty(videoList)) {
            videoService.saveBatch(videoList);
        }
        return fileRespList;
    }
}
