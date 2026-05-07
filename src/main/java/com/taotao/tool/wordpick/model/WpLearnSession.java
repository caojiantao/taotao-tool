package com.taotao.tool.wordpick.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户学习会话。
 */
@Getter
@Setter
@TableName("wp_learn_session")
public class WpLearnSession {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer durationSeconds;
    private Integer knownActionCount;
    private Integer knownWordCount;
    private Integer unknownActionCount;
    private Integer unknownWordCount;
    private Integer state;
}
