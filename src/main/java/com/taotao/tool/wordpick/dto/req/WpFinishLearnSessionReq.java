package com.taotao.tool.wordpick.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WpFinishLearnSessionReq {

    /**
     * 学习会话 ID。
     */
    private Long learnSessionId;
}
