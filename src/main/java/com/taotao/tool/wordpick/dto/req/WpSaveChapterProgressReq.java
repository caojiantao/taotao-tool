package com.taotao.tool.wordpick.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpSaveChapterProgressReq {

    private Integer chapterId;
    private Integer state;
}
