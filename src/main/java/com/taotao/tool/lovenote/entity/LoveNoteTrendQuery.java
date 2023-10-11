package com.taotao.tool.lovenote.entity;

import lombok.Data;

@Data
public class LoveNoteTrendQuery {

    private Integer cpId;
    private String openid;

    private Integer page;
    private Integer size;
}
