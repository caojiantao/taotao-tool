package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WpChapterDetailResp {

    private Integer chapterId;
    private String chapterName;
    private Integer wordCount;
    private WpChapterLearnSummaryResp learnSummary;
}
