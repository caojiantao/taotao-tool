package com.taotao.tool.wordpick.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户单词行为流水
 * <p>
 * 事件溯源原表，用户章节学习摘要等上层数据由此推导。
 * action 分段编码：1x=判断类（11=认识 12=不认识）。
 */
@Getter
@Setter
@TableName("wp_word_action")
public class WpWordAction {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private Long learnSessionId;
    private Integer wordId;
    private Short action;
    private LocalDateTime createdAt;
}
