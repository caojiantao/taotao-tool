package com.taotao.tool.lovenote.controller;

import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.exception.ApiException;
import com.taotao.tool.lovenote.entity.LoveNoteTrendDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendQuery;
import com.taotao.tool.lovenote.entity.LoveNoteTrendVo;
import com.taotao.tool.lovenote.other.LoveNoteLoginApi;
import com.taotao.tool.lovenote.service.ILoveNoteTrendService;
import com.taotao.tool.yml.UploadYml;
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

    @LoveNoteLoginApi
    @PostMapping("/uploadImage")
    public ApiResp<String> uploadImage(@RequestPart MultipartFile file) {
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
        return ApiResp.success(filename);
    }

    @LoveNoteLoginApi
    @PostMapping("/addTrend")
    public ApiResp<Void> addTrend(@RequestBody LoveNoteTrendDto trendDto) {
        loveNoteTrendService.addTrend(trendDto);
        return ApiResp.success();
    }

    @PostMapping("/getTrendList")
    public ApiResp<List<LoveNoteTrendVo>> getTrendList(LoveNoteTrendQuery query) {
        List<LoveNoteTrendVo> list = loveNoteTrendService.getLoveNoteTrendList(query);
        return ApiResp.success(list);
    }
}
