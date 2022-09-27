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
import com.taotao.tool.yml.VideoYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private VideoYml videoYml;
    @Autowired
    private IPicService picService;
    @Autowired
    private IVideoService videoService;

    public List<Pic> doUploadPic(List<MultipartFile> files) throws IOException {
        String diskPath = picYml.getPath();
        List<Pic> picList = Lists.newArrayList();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            ApiAssertUtils.notNull(contentType, "文件类型不能为空");
            ApiAssertUtils.isTrue(contentType.startsWith("image"), "仅支持图片类型");
            String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            Pic originPic = picService.getByMd5(md5);
            if (Objects.nonNull(originPic)) {
                log.info("act=doUploadPic type=alreadyExist filename={}", originPic.getFilename());
                picList.add(originPic);
                continue;
            }
            String ext = contentType.substring(contentType.lastIndexOf("/") + 1);
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + ext;
            File newFile = new File(diskPath, newFilename);
            file.transferTo(newFile);
            log.info("act=batchUploadPic type=saveDisk filename={} newFilename={}", filename, newFilename);
            Pic pic = new Pic();
            pic.setFilename(newFilename);
            pic.setMd5(md5);
            pic.setBytes(newFile.length());
            pic.setContentType(contentType);
            pic.setGmtCreate(LocalDateTime.now());
            pic.setGmtModified(LocalDateTime.now());
            picList.add(pic);
        }
        if (!CollectionUtils.isEmpty(picList)) {
            picService.saveBatch(picList);
        }
        return picList;
    }

    public List<Video> doUploadVideo(List<MultipartFile> files) throws IOException {
        String diskPath = videoYml.getPath();
        List<Video> videoList = Lists.newArrayList();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            ApiAssertUtils.notNull(contentType, "文件类型不能为空");
            ApiAssertUtils.isTrue(contentType.startsWith("video"), "仅支持视频类型");
            String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            Video originVideo = videoService.getByMd5(md5);
            if (Objects.nonNull(originVideo)) {
                log.info("act=doUploadVideo type=alreadyExist filename={}", originVideo.getFilename());
                videoList.add(originVideo);
                continue;
            }
            String ext = contentType.substring(contentType.lastIndexOf("/") + 1);
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + ext;
            File newFile = new File(diskPath, newFilename);
            file.transferTo(newFile);
            log.info("act=doUploadVideo type=saveDisk filename={} newFilename={}", filename, newFilename);
            Video video = new Video();
            video.setFilename(newFilename);
            video.setMd5(md5);
            video.setBytes(newFile.length());
            video.setContentType(contentType);
            video.setGmtCreate(LocalDateTime.now());
            video.setGmtModified(LocalDateTime.now());
            videoList.add(video);
        }
        if (!CollectionUtils.isEmpty(videoList)) {
            videoService.saveBatch(videoList);
        }
        return videoList;
    }
}
