package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WpWordbookImportResp {

    private String dataset;
    private String categoryName;
    private Integer chapterCount;
    private Integer wordCount;
    private Integer senseCount;
    private Integer exampleCount;
}
