package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.wordpick.dto.req.WpSaveMarkReq;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.service.IWpWordMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wp/word")
public class WpWordController {

    @Autowired
    private IWpWordMarkService wordMarkService;

    @RequireLogin
    @PostMapping("/saveMark")
    public ApiResult<Void> saveMark(@RequestBody WpSaveMarkReq req) {
        Integer userId = LoginUtils.getCurrentUser().getId();
        wordMarkService.saveMark(userId, req);
        return ApiResult.success(null);
    }

    @RequireLogin
    @GetMapping("/listMark")
    public ApiResult<List<WpWordResp>> listMark(@RequestParam(required = false) Integer categoryId,
                                                @RequestParam(required = false) Integer state) {
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(wordMarkService.listMark(userId, categoryId, state));
    }
}
