package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.wordpick.dto.req.WpSaveMarkReq;
import com.taotao.tool.wordpick.dto.req.WpSaveWordActionReq;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import com.taotao.tool.wordpick.service.IWpWordActionService;
import com.taotao.tool.wordpick.service.IWpWordMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wp/word")
public class WpWordController {

    @Autowired
    private IWpWordMarkService wordMarkService;

    @Autowired
    private IWpCategoryService categoryService;

    @Autowired
    private IWpWordActionService wordActionService;

    @GetMapping("/listWords")
    public ApiResult<List<WpWordResp>> listWords(@RequestParam Integer chapterId) {
        return ApiResult.success(categoryService.listWords(chapterId));
    }

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

    @RequireLogin
    @PostMapping("/saveWordAction")
    public ApiResult<Void> saveWordAction(@RequestBody WpSaveWordActionReq req) {
        log.info("saveWordAction_param, req={}", req);
        Integer userId = LoginUtils.getCurrentUser().getId();
        wordActionService.saveAction(userId, req.getLearnSessionId(), req.getWordId(), req.getAction());
        return ApiResult.success(null);
    }
}
