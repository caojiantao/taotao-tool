package com.taotao.tool.common.dto;

import com.taotao.tool.common.constants.EMediaType;
import lombok.Data;

@Data
public class MediaDTO {

    private EMediaType type;

    private Image image;

    @Data
    public static class Image {
        private String url;
    }
}
