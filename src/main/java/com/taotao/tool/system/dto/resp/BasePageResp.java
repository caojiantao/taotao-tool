package com.taotao.tool.system.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageResp<T> {

    private List<T> rows;
    private Long total;
}
