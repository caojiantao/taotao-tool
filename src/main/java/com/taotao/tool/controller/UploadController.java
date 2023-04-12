package com.taotao.tool.controller;

import com.taotao.tool.annotation.RequireLogin;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.exception.ApiException;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.yml.UploadYml;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadYml uploadYml;

    @RequireLogin
    @PostMapping("/image")
    public ApiResp<String> uploadImage(@RequestPart MultipartFile file, boolean keepName, boolean compress) {
        UploadYml.Image image = uploadYml.getImage();
        // 是否保持
        String filename = keepName ? file.getOriginalFilename() : (UUID.randomUUID() + ".jpg");
        assert filename != null;
        File outFile = new File(image.getPath(), filename);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(outFile.toPath())) {
            if (compress) {
                // 压缩
                Thumbnails.of(is)
                        .outputFormat("jpg")
                        .outputQuality(image.getQuality())
                        .scale(1)
                        .toFile(outFile);
            } else {
                // 不压缩
                StreamUtils.copy(is, os);
            }
        } catch (Exception e) {
            log.error("act=uploadImage", e);
            throw new ApiException(-1, e.getMessage());
        }
        return ApiResp.success(filename);
    }

    @RequireLogin
    @PostMapping("/image/delete")
    public ApiResp<Void> deleteImage(String filename) {
        UploadYml.Image image = uploadYml.getImage();
        File file = new File(image.getPath(), filename);
        ApiAssertUtils.isTrue(file.delete(), "删除失败");
        return ApiResp.success();
    }
}
