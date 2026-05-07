package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.wordpick.dto.resp.WpChapterDetailResp;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wp/chapter")
public class WpChapterController {

    @Autowired
    private IWpCategoryService categoryService;

    @GetMapping("/listChapters")
    public ApiResult<List<WpChapterResp>> listChapters(@RequestParam Integer categoryId) {
        return ApiResult.success(categoryService.listChapters(categoryId));
    }

    @RequireLogin
    @GetMapping("/getChapterDetail")
    public ApiResult<WpChapterDetailResp> getChapterDetail(@RequestParam Integer chapterId) {
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(categoryService.getChapterDetail(userId, chapterId));
    }
}
