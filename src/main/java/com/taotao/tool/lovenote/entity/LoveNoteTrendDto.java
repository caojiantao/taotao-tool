package com.taotao.tool.lovenote.entity;

import lombok.Data;

import java.util.List;

@Data
public class LoveNoteTrendDto {

    private String content;

    private List<LoveNoteTrendMediaDto> mediaList;
}
