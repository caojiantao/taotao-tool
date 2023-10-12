package com.taotao.tool.admin.controller;

import com.taotao.tool.admin.annotation.RequireLogin;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.admin.model.Dictionary;
import com.taotao.tool.admin.service.IDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    @GetMapping("/listDictionary")
    public ApiResult<List<Dictionary>> listDictionary() {
        List<Dictionary> list = dictionaryService.list();
        return ApiResult.success(list);
    }

    @RequireLogin
    @PostMapping("/saveDictionary")
    public ApiResult<Void> saveDictionary(@RequestBody Dictionary dictionary) {
        dictionaryService.saveOrUpdateByKey(dictionary);
        return ApiResult.success();
    }

    @RequireLogin
    @PostMapping("/removeDictionary")
    public ApiResult<Void> removeDictionary(Integer id) {
        dictionaryService.removeById(id);
        return ApiResult.success();
    }
}
