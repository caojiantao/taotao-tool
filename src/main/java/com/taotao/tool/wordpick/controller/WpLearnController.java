package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.wordpick.dto.req.WpFinishLearnSessionReq;
import com.taotao.tool.wordpick.dto.resp.WpLearnSessionResp;
import com.taotao.tool.wordpick.service.IWpLearnSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wp/learn")
public class WpLearnController {

    @Autowired
    private IWpLearnSessionService learnSessionService;

    @RequireLogin
    @PostMapping("/startSession")
    public ApiResult<WpLearnSessionResp> startSession() {
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(learnSessionService.startSession(userId));
    }

    @RequireLogin
    @PostMapping("/finishSession")
    public ApiResult<WpLearnSessionResp> finishSession(@RequestBody WpFinishLearnSessionReq req) {
        log.info("finishLearnSession_param, req={}", req);
        Integer userId = LoginUtils.getCurrentUser().getId();
        return ApiResult.success(learnSessionService.finishSession(userId, req.getLearnSessionId()));
    }
}
