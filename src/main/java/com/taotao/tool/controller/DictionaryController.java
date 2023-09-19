package com.taotao.tool.controller;

import com.taotao.tool.annotation.RequireLogin;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.model.Dictionary;
import com.taotao.tool.service.IDictionaryService;
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
    public ApiResp<List<Dictionary>> listDictionary() {
        List<Dictionary> list = dictionaryService.list();
        return ApiResp.success(list);
    }

    @RequireLogin
    @PostMapping("/saveDictionary")
    public ApiResp<Void> saveDictionary(@RequestBody Dictionary dictionary) {
        dictionaryService.saveOrUpdateByKey(dictionary);
        return ApiResp.success();
    }

    @RequireLogin
    @PostMapping("/removeDictionary")
    public ApiResp<Void> removeDictionary(Integer id) {
        dictionaryService.removeById(id);
        return ApiResp.success();
    }
}
