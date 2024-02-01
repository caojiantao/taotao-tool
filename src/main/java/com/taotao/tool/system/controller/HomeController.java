package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.dto.amap.WeatherInfoResp;
import com.taotao.tool.system.dto.resp.HomeExtraResp;
import com.taotao.tool.system.model.User;
import com.taotao.tool.system.service.AmapService;
import com.taotao.tool.system.util.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AmapService amapService;

    @RequireLogin
    @GetMapping("/getHomeExtraResp")
    public ApiResult<HomeExtraResp> getHomeExtraResp() {
        HomeExtraResp resp = new HomeExtraResp();
        User currentUser = LoginUtils.getCurrentUser();
        HomeExtraResp.UserInfo userInfo = new HomeExtraResp.UserInfo();
        userInfo.setNickname(currentUser.getUsername());
        resp.setUserInfo(userInfo);
        WeatherInfoResp all = amapService.getWeatherInfoByCity("110000");
        HomeExtraResp.WeatherInfo weatherInfo = new HomeExtraResp.WeatherInfo();
        weatherInfo.setForecasts(all.getForecasts().get(0));
        resp.setWeatherInfo(weatherInfo);
        return ApiResult.success(resp);
    }
}
