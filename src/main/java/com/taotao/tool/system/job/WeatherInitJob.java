package com.taotao.tool.system.job;

import com.taotao.tool.system.dto.amap.WeatherInfoReq;
import com.taotao.tool.system.service.AmapService;
import com.taotao.tool.system.yml.AmapYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherInitJob implements InitializingBean {

    @Autowired
    private AmapYml amapYml;
    @Autowired
    private AmapService amapService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void initWeatherAll() throws Exception {
        // 暂时只写入北京的天气数据
        String city = "110000";
        WeatherInfoReq req = WeatherInfoReq.builder()
                .key(amapYml.getKey())
                .city(city)
                .extensions("all")
                .build();
        amapService.weatherInfo(req);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 保证服务启动就有数据
        initWeatherAll();
    }
}
