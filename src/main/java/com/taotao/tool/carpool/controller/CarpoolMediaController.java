package com.taotao.tool.carpool.controller;

import com.taotao.tool.carpool.service.ICarpoolMediaService;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.exception.ApiException;
import com.taotao.tool.spring.yml.UploadYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Slf4j
@Controller
@RequestMapping("/carpool/media")
public class CarpoolMediaController {

    @Autowired
    private UploadYml uploadYml;

    @Autowired
    private ICarpoolMediaService carpoolMediaService;

    @PostMapping("/uploadImage")
    public ApiResult<String> uploadImage(@RequestPart MultipartFile file) {
        String fname = file.getOriginalFilename();
        String suffix = StringUtils.hasText(fname) ? fname.substring(fname.lastIndexOf(".")) : ".jpg";
        UploadYml.Image image = uploadYml.getImage();
        String filename = UUID.randomUUID() + suffix;
        File outFile = new File(image.getPath() + "/carpool", filename);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(outFile.toPath())) {
            StreamUtils.copy(is, os);
        } catch (Exception e) {
            log.error("上传图片异常", e);
            throw new ApiException(-1, e.getMessage());
        }
        String mediaUrl = carpoolMediaService.getMediaUrl(filename);
        return ApiResult.success(mediaUrl);
    }
}
