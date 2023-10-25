package com.taotao.tool.lovenote.entity;

import com.taotao.tool.lovenote.constant.ELoveNoteTrendMediaType;
import lombok.Data;

@Data
public class LoveNoteTrendMediaDto {

    private ELoveNoteTrendMediaType type;

    private Image image;

    @Data
    public static class Image {
        private String url;
    }
}
