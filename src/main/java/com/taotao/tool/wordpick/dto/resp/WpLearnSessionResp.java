package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WpLearnSessionResp {

    private Long learnSessionId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer durationSeconds;
    private Integer knownActionCount;
    private Integer knownWordCount;
    private Integer unknownActionCount;
    private Integer unknownWordCount;
    private Integer state;
}
