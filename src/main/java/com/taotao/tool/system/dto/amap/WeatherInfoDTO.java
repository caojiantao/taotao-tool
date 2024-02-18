package com.taotao.tool.system.dto.amap;

import lombok.Data;

import java.util.List;

@Data
public class WeatherInfoDTO {

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<Live> lives;
    private List<Forecast> forecasts;

    @Data
    public static class Live {
        private String province;
        private String city;
        private String adcode;
        private String weather;
        private String temperature;
        private String winddirection;
        private String windpower;
        private String humidity;
        private String reporttime;
        private String temperature_float;
        private String humidity_float;
    }

    @Data
    public static class Forecast {

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
