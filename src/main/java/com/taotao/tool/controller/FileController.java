package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.tool.annotation.RequireLogin;
import com.taotao.tool.dto.req.FilePageReq;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.model.File;
import com.taotao.tool.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping("/batchUpload")
    @RequireLogin
    public ApiResp<Void> batchUpload(@RequestPart List<MultipartFile> files) throws Exception {
        fileService.doBatchUpload(files);
        return ApiResp.success(null);
    }

    @GetMapping("/getFilePage")
    public ApiResp<BasePageResp<File>> getFilePage(@Validated FilePageReq req) {
        IPage<File> page = new Page<>(req.getCurrent(), req.getSize());
        QueryChainWrapper<File> queryChainWrapper = fileService.query().orderByDesc("gmt_create");
        if (Objects.nonNull(req.getFileType())) {
            queryChainWrapper.eq("file_type", req.getFileType());
        }
        queryChainWrapper.page(page);
        BasePageResp<File> resp = new BasePageResp<>(page.getRecords(), page.getTotal());
        return ApiResp.success(resp);
    }
}
