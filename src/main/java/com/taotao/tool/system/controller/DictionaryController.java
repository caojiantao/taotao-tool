package com.taotao.tool.system.controller;

import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.model.Dictionary;
import com.taotao.tool.system.service.IDictionaryService;
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

    @GetMapping("/getDictionaryByItemKey")
    public ApiResult<Dictionary> getDictionaryByItemKey(String itemKey) {
        Dictionary dictionary = dictionaryService.query().eq("item_key", itemKey).one();
        return ApiResult.success(dictionary);
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
