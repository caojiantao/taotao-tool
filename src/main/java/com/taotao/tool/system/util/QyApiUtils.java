package com.taotao.tool.system.util;

import com.taotao.tool.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信 API
 */
@Slf4j
public class QyApiUtils {

    public static void sendMessage(String webhook, String content) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> markdown = new HashMap<>();
        map.put("msgtype", "markdown");
        map.put("markdown", markdown);
        markdown.put("content", content);
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.POST)
                .uri(webhook)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonUtils.toJson(map))
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10));
        String html = mono.block();
        log.info("act=QyApiUtils.sendMessage webhook={} content={} html={}", webhook, markdown, html);
    }
}
