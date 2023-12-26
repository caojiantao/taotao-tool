package com.taotao.tool.admin.controller;

import com.taotao.tool.admin.annotation.RequireLogin;
import com.taotao.tool.admin.dto.req.SqlExecuteReq;
import com.taotao.tool.admin.dto.resp.SqlExecuteResp;
import com.taotao.tool.common.dto.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequireLogin
    @PostMapping("/execute")
    public ApiResult<SqlExecuteResp> execute(@RequestBody SqlExecuteReq req) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(req.getSql());
        SqlExecuteResp resp = new SqlExecuteResp();
        for (Map<String, Object> map : list) {
            if (CollectionUtils.isEmpty(resp.getHead())) {
                map.keySet().forEach(resp.getHead()::add);
            }
            List<Object> data = new ArrayList<>(map.values());
            resp.getDataList().add(data);
        }
        return ApiResult.success(resp);
    }
}
