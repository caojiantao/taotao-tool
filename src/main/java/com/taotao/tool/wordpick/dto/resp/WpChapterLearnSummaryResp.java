package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WpChapterLearnSummaryResp {

    private Integer knownActionCount;
    private Integer unknownActionCount;
    private String lastLearnTime;
}
