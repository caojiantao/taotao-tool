package com.taotao.tool.system.service;

import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.system.dto.amap.WeatherInfoReq;
import com.taotao.tool.system.dto.amap.WeatherInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class AmapService {

    /**
     * 天气预报缓存
     */
    private final Map<String, WeatherInfoResp> weatherCache = new ConcurrentHashMap<>();

    public void weatherInfo(WeatherInfoReq req) {
        String uriString = UriComponentsBuilder.fromUriString("https://restapi.amap.com/v3/weather/weatherInfo")
                .queryParam("key", req.getKey())
                .queryParam("city", req.getCity())
                .queryParam("extensions", req.getExtensions())
                .toUriString();
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.GET)
                .uri(uriString)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10));
        String json = null;
        try {
            json = mono.block();
            log.info("act=AmapService.weatherInfo req={} resp={}", JsonUtils.toJson(req), json);
        } catch (Exception e) {
            log.info("act=AmapService.weatherInfo req={}", JsonUtils.toJson(req), e);
        }
        if (Objects.nonNull(json)) {
            WeatherInfoResp resp = JsonUtils.parse(json, WeatherInfoResp.class);
            weatherCache.put(req.getCity(), resp);
        }
    }

    public WeatherInfoResp getWeatherInfoByCity(String city) {
        return weatherCache.get(city);
    }
}
