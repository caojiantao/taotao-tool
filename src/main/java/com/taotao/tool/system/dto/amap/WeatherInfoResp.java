package com.taotao.tool.system.dto.amap;

import lombok.Data;

import java.util.List;

@Data
public class WeatherInfoResp {

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<Forecasts> forecasts;

    @Data
    public static class Forecasts {

        private String city;
        private String adcode;
        private String province;
        private String reporttime;
        private List<Casts> casts;
    }

    @Data
    public static class Casts {

        private String date;
        private String week;
        private String dayweather;
        private String nightweather;
        private String daytemp;
        private String nighttemp;
        private String daywind;
        private String nightwind;
        private String daypower;
        private String nightpower;
        private String daytemp_float;
        private String nighttemp_float;
    }
}
