package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/metric")
public class MetricController {

    @Autowired
    private MetricsEndpoint endpoint;

    @GetMapping("/list")
    public ApiResult<List<MetricsEndpoint.MetricResponse>> list() {
        List<MetricsEndpoint.MetricResponse> list = new ArrayList<>();
        for (String name : endpoint.listNames().getNames()) {
            MetricsEndpoint.MetricResponse metric = endpoint.metric(name, null);
            list.add(metric);
        }
        return ApiResult.success(list);
    }
}
