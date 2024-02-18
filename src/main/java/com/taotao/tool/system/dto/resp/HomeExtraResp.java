package com.taotao.tool.system.dto.resp;

import com.taotao.tool.system.dto.amap.WeatherInfoDTO;
import lombok.Data;

@Data
public class HomeExtraResp {

    private UserInfo userInfo;
    private WeatherInfoDTO.Live weatherInfo;

    @Data
    public static class UserInfo {
        private String nickname;
        private String avatar;
    }
}
