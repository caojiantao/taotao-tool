package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WpHomeSummaryResp {

    private Integer learnedWordActionCount;
    private Integer learnedChapterCount;
    private String durationText;
}
