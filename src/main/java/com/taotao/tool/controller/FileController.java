package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.tool.dto.req.FilePageReq;
import com.taotao.tool.dto.req.UploadFileReq;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.model.File;
import com.taotao.tool.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

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
    public ApiResp<Void> batchUpload(UploadFileReq uploadReq) throws Exception {
        fileService.doUploadFile(uploadReq.getFileItems());
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
