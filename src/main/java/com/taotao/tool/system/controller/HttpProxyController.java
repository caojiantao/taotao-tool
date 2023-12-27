package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/httpProxy")
public class HttpProxyController {

    /**
     * HTTP 代理，解决
     */
    @RequestMapping("/doExecute")
    public ApiResult<Object> doExecute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String targetUrl) throws Exception {
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.valueOf(httpServletRequest.getMethod()))
                .uri(targetUrl)
                .retrieve()
                .bodyToMono(String.class);
        String data = mono.block();
        return ApiResult.success(data);
    }
}
