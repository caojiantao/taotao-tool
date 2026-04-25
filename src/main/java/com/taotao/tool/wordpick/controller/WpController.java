package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.wordpick.dto.resp.WpCategoryResp;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wp")
public class WpController {

    @Autowired
    private IWpCategoryService categoryService;

    @GetMapping("/categories")
    public ApiResult<List<WpCategoryResp>> listCategories() {
        return ApiResult.success(categoryService.listCategories());
    }

    @GetMapping("/chapters")
    public ApiResult<List<WpChapterResp>> listChapters(@RequestParam Integer categoryId) {
        return ApiResult.success(categoryService.listChapters(categoryId));
    }

    @GetMapping("/words")
    public ApiResult<List<WpWordResp>> listWords(@RequestParam Integer chapterId) {
        return ApiResult.success(categoryService.listWords(chapterId));
    }
}
