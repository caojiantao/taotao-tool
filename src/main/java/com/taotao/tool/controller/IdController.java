package com.taotao.tool.controller;

import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.util.SnowFlakeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/id")
public class IdController {

    @GetMapping("/get")
    public ApiResp<Long> get() {
        long id = SnowFlakeUtils.getId();
        return ApiResp.success(id);
    }
}
