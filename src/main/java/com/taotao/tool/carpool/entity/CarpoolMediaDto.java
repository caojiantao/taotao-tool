package com.taotao.tool.carpool.entity;

import com.taotao.tool.carpool.constant.ECarpoolMediaType;
import lombok.Data;

@Data
public class CarpoolMediaDto {

    private ECarpoolMediaType type;

    private Image image;

    @Data
    public static class Image {
        private String url;
    }
}
