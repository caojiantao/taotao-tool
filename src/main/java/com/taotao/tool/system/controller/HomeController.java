package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.dto.resp.HomeExtraResp;
import com.taotao.tool.system.model.SystemUser;
import com.taotao.tool.system.util.LoginUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @RequireLogin
    @GetMapping("/getHomeExtraResp")
    public ApiResult<HomeExtraResp> getHomeExtraResp() {
        HomeExtraResp resp = new HomeExtraResp();
        SystemUser currentUser = LoginUtils.getCurrentUser();
        HomeExtraResp.UserInfo userInfo = new HomeExtraResp.UserInfo();
        userInfo.setNickname(currentUser.getUsername());
        userInfo.setAvatar(currentUser.getAvatar());
        resp.setUserInfo(userInfo);
        return ApiResult.success(resp);
    }
}
