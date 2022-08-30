package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.tool.dto.req.BasePageReq;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.model.Pic;
import com.taotao.tool.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
@RequestMapping("/pic")
public class PicController {

    @Autowired
    private IPicService picService;

    @PostMapping("/batchUploadPic")
    public ApiResp<Void> batchUploadPic(MultipartHttpServletRequest request) throws Exception {
        picService.doUpload(request);
        return ApiResp.success(null);
    }

    @GetMapping("/getPicPage")
    public ApiResp<BasePageResp<Pic>> getPicPage(@Validated BasePageReq req) {
        IPage<Pic> page = new Page<>(req.getPage(), req.getSize());
        picService.query()
                .orderByDesc("gmt_create")
                .page(page);
        BasePageResp<Pic> resp = new BasePageResp<>(page.getRecords(), page.getTotal());
        return ApiResp.success(resp);
    }
}
