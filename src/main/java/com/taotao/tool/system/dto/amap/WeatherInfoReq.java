package com.taotao.tool.system.dto.amap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoReq {

    private String key;
    private String city;
    private String extensions;
}
