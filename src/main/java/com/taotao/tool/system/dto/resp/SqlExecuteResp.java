package com.taotao.tool.system.dto.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SqlExecuteResp {

    private List<String> head = new ArrayList<>();
    private List<List<Object>> dataList = new ArrayList<>();
}
