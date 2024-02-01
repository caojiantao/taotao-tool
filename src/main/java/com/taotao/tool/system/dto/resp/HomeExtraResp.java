package com.taotao.tool.system.dto.resp;

import com.taotao.tool.system.dto.amap.WeatherInfoResp;
import lombok.Data;

@Data
public class HomeExtraResp {

    private UserInfo userInfo;
    private WeatherInfo weatherInfo;

    @Data
    public static class UserInfo {
        private String nickname;
    }

    @Data
    public static class WeatherInfo {
        private WeatherInfoResp.Forecasts forecasts;
    }
}
