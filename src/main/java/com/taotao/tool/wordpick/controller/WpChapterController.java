package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.wordpick.dto.req.WpSaveChapterProgressReq;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.model.WpUserChapterProgress;
import com.taotao.tool.wordpick.service.IWpUserChapterProgressService;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wp/chapter")
public class WpChapterController {

    @Autowired
    private IWpUserChapterProgressService progressService;

    @Autowired
    private IWpCategoryService categoryService;

    @GetMapping("/listChapters")
    public ApiResult<List<WpChapterResp>> listChapters(@RequestParam Integer categoryId) {
        return ApiResult.success(categoryService.listChapters(categoryId));
    }

    @RequireLogin
    @PostMapping("/saveChapterProgress")
    public ApiResult<Void> saveChapterProgress(@RequestBody WpSaveChapterProgressReq req) {
        Integer userId = LoginUtils.getCurrentUser().getId();
        progressService.saveChapterProgress(userId, req);
        return ApiResult.success(null);
    }

    @RequireLogin
    @GetMapping("/getChapterProgress")
    public ApiResult<WpUserChapterProgress> getChapterProgress(@RequestParam Integer chapterId) {
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(progressService.getChapterProgress(userId, chapterId));
    }

    @RequireLogin
    @GetMapping("/listLearningChapters")
    public ApiResult<List<WpChapterResp>> listLearningChapters() {
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(progressService.listLearningChapters(userId));
    }
}
