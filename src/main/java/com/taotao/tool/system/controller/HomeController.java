package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.dto.amap.WeatherInfoDTO;
import com.taotao.tool.system.dto.amap.WeatherInfoReq;
import com.taotao.tool.system.dto.resp.HomeExtraResp;
import com.taotao.tool.system.model.SystemUser;
import com.taotao.tool.system.service.AmapService;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.system.yml.AmapYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AmapYml amapYml;
    @Autowired
    private AmapService amapService;

    @RequireLogin
    @GetMapping("/getHomeExtraResp")
    public ApiResult<HomeExtraResp> getHomeExtraResp() {
        HomeExtraResp resp = new HomeExtraResp();
        SystemUser currentUser = LoginUtils.getCurrentUser();
        HomeExtraResp.UserInfo userInfo = new HomeExtraResp.UserInfo();
        userInfo.setNickname(currentUser.getUsername());
        userInfo.setAvatar(currentUser.getAvatar());
        resp.setUserInfo(userInfo);
        String city = "110000";
        WeatherInfoReq req = WeatherInfoReq.builder()
                .key(amapYml.getKey())
                .city(city)
                .extensions("base")
                .build();
        WeatherInfoDTO all = amapService.weatherInfo(req);
        resp.setWeatherInfo(all.getLives().get(0));
        return ApiResult.success(resp);
    }


    @RequireLogin
    @GetMapping("/getWeatherForecasts")
    public ApiResult<WeatherInfoDTO.Forecast> getWeatherForecasts() {
        HomeExtraResp resp = new HomeExtraResp();
        SystemUser currentUser = LoginUtils.getCurrentUser();
        HomeExtraResp.UserInfo userInfo = new HomeExtraResp.UserInfo();
        userInfo.setNickname(currentUser.getUsername());
        userInfo.setAvatar(currentUser.getAvatar());
        resp.setUserInfo(userInfo);
        String city = "110000";
        WeatherInfoReq req = WeatherInfoReq.builder()
                .key(amapYml.getKey())
                .city(city)
                .extensions("all")
                .build();
        WeatherInfoDTO all = amapService.weatherInfo(req);
        return ApiResult.success(all.getForecasts().get(0));
    }
}
