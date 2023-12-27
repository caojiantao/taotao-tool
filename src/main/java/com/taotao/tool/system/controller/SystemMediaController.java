package com.taotao.tool.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.tool.common.constants.EMediaType;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.exception.ApiException;
import com.taotao.tool.system.dto.req.SystemMediaListReq;
import com.taotao.tool.system.model.SystemMedia;
import com.taotao.tool.system.service.ISystemMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/system/media")
public class SystemMediaController {

    @Autowired
    private ISystemMediaService systemMediaService;

    @Transactional
    @PostMapping("/upload")
    public ApiResult<SystemMedia> upload(@RequestPart MultipartFile file, @NotEmpty String bucket) {
        // 是否保持
        assert file.getOriginalFilename() != null;
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID() + suffix;
        File outFile = new File("/var/www/media/" + bucket, filename);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(outFile.toPath())
        ) {
            String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
            SystemMedia media = systemMediaService.query().eq("content_md5", md5).one();
            if (Objects.nonNull(media)) {
                return ApiResult.success(media);
            }
            media = new SystemMedia();
            media.setBucket(bucket);
            media.setMediaType(EMediaType.IMAGE.name());
            media.setFilename(filename);
            media.setContentMd5(md5);
            media.setContentLength(file.getSize());
            systemMediaService.save(media);
            StreamUtils.copy(is, os);
            return ApiResult.success(media);
        } catch (Exception e) {
            log.error("act=uploadImage", e);
            throw new ApiException(-1, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ApiResult<List<SystemMedia>> list(@Valid SystemMediaListReq req) {
        Page<SystemMedia> page = new Page<>(req.getCurrent(), req.getSize());
        systemMediaService.page(page);
        return ApiResult.success(page.getRecords());
    }

    @Transactional
    @PostMapping("/delete")
    public ApiResult<Void> delete(Integer id) {
        SystemMedia media = systemMediaService.getById(id);
        if (Objects.isNull(media)) {
            return ApiResult.success();
        }
        File file = new File("/var/www/media/" + media.getBucket(), media.getFilename());
        file.delete();
        systemMediaService.removeById(id);
        return ApiResult.success();
    }
}
