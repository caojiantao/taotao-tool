package com.taotao.tool.wordpick.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WpCategoryResp {

    private Integer id;
    private String name;
    private String icon;
    private Integer sort;
}
