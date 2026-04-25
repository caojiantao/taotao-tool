package com.taotao.tool.wordpick.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WpWordResp {

    private Integer id;
    private String word;
    private String phoneticUs;
    private String phoneticUk;
    private Integer sort;
    private List<WpSenseResp> senses;
    private List<WpExampleResp> examples;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WpSenseResp {
        private String pos;
        private String meaning;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WpExampleResp {
        private String sentence;
        private String translation;
    }
}
