package com.taotao.tool.lovenote.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.admin.dto.resp.LoveNoteLoginResp;
import com.taotao.tool.common.exception.ApiException;
import com.taotao.tool.lovenote.entity.LoveNoteTrendDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendQuery;
import com.taotao.tool.lovenote.entity.LoveNoteTrendVo;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.other.LoveNoteLoginApi;
import com.taotao.tool.lovenote.service.ILoveNoteTrendService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.taotao.tool.spring.yml.UploadYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/loveNote")
public class LoveNoteController {

    @Autowired
    private UploadYml uploadYml;

    @Autowired
    private ILoveNoteTrendService loveNoteTrendService;

    @Autowired
    private ILoveNoteUserService loveNoteUserService;

    @PostMapping("/login")
    public ApiResult<LoveNoteLoginResp> login(String code) {
        LoveNoteLoginResp resp = loveNoteUserService.login(code);
        return ApiResult.success(resp);
    }

    @PostMapping("/register")
    public ApiResult<LoveNoteLoginResp> register(@RequestBody LoveNoteUser user) {
        LoveNoteLoginResp resp = loveNoteUserService.register(user);
        return ApiResult.success(resp);
    }

    @LoveNoteLoginApi
    @PostMapping("/uploadImage")
    public ApiResult<String> uploadImage(@RequestPart MultipartFile file) {
        String fname = file.getOriginalFilename();
        String suffix = StringUtils.hasText(fname) ? fname.substring(fname.lastIndexOf(".")) : ".jpg";
        UploadYml.Image image = uploadYml.getImage();
        String filename = UUID.randomUUID() + suffix;
        File outFile = new File(image.getPath() + "/love-note", filename);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(outFile.toPath())) {
            StreamUtils.copy(is, os);
        } catch (Exception e) {
            log.error("上传图片异常", e);
            throw new ApiException(-1, e.getMessage());
        }
        return ApiResult.success(filename);
    }

    @LoveNoteLoginApi
    @PostMapping("/addTrend")
    public ApiResult<Void> addTrend(@RequestBody LoveNoteTrendDto trendDto) {
        loveNoteTrendService.addTrend(trendDto);
        return ApiResult.success();
    }

    @PostMapping("/getTrendList")
    public ApiResult<List<LoveNoteTrendVo>> getTrendList(LoveNoteTrendQuery query) {
        List<LoveNoteTrendVo> list = loveNoteTrendService.getLoveNoteTrendList(query);
        return ApiResult.success(list);
    }
}
