package com.taotao.tool.wordpick.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WpSaveWordActionReq {

    /**
     * 学习会话 ID。
     */
    private Long learnSessionId;

    /**
     * 单词 ID。
     */
    private Integer wordId;

    /**
     * 行为类型，分段编码：1x=判断类（11=认识 12=不认识）。
     */
    private Short action;
}
