package com.taotao.tool.controller;

import com.google.common.collect.Sets;
import com.taotao.tool.dto.resp.ApiResp;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Set;

@RestController
@RequestMapping("/httpProxy")
public class HttpProxyController {

    private Set<String> headers = Sets.newHashSet("content-type", "cookie");

    /**
     * HTTP 代理，解决
     */
    @RequestMapping("/doExecute")
    public ApiResp<Object> doExecute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String targetUrl) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.execute(
                URLDecoder.decode(targetUrl, StandardCharsets.UTF_8.name()),
                HttpMethod.valueOf(httpServletRequest.getMethod()),
                request -> {
                    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        if (headers.contains(name)) {
                            String value = httpServletRequest.getHeader(name);
                            request.getHeaders().add(name, value);
                        }
                    }
                    ServletInputStream is = httpServletRequest.getInputStream();
                    StreamUtils.copy(is, request.getBody());
                },
                response -> StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8)
        );
        return ApiResp.success(data);
    }
}
