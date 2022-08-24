package com.taotao.tool.dto.resp;

import com.taotao.tool.model.Pic;
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
