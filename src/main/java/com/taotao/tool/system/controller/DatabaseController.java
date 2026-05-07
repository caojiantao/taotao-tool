package com.taotao.tool.system.controller;

import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.dto.req.SqlExecuteReq;
import com.taotao.tool.system.dto.resp.SqlExecuteResp;
import com.taotao.tool.common.dto.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequireLogin
    @GetMapping("/tables")
    public ApiResult<Map<String, List<String>>> tables() {
        String sql = "SELECT table_name FROM information_schema.tables "
                + "WHERE table_schema = DATABASE() ORDER BY table_name";
        List<String> tables = jdbcTemplate.queryForList(sql, String.class);
        Map<String, List<String>> schema = new LinkedHashMap<>();
        tables.forEach(table -> schema.put(table, columns(table)));
        return ApiResult.success(schema);
    }

    private List<String> columns(String table) {
        String sql = "SELECT column_name FROM information_schema.columns "
                + "WHERE table_schema = DATABASE() AND table_name = ? ORDER BY ordinal_position";
        return jdbcTemplate.queryForList(sql, String.class, table);
    }

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
