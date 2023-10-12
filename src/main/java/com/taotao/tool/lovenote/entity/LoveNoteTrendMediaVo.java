package com.taotao.tool.lovenote.entity;

import com.taotao.tool.lovenote.constant.ELoveNoteTrendMediaType;
import com.taotao.tool.common.util.JsonUtils;
import lombok.Data;

@Data
public class LoveNoteTrendMediaVo {

    private Integer id;

    private ELoveNoteTrendMediaType type;

    private Image image;

    @Data
    public static class Image {
        private String url;
    }

    public String toContent() {
        Object data = null;
        switch (type) {
            case IMAGE:
                data = image;
                break;
            default:
                break;
        }
        return JsonUtils.toJson(data);
    }
}
