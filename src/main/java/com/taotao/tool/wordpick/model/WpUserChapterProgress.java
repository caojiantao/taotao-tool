package com.taotao.tool.wordpick.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("wp_user_chapter_progress")
public class WpUserChapterProgress {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private Integer chapterId;
    private Integer state;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
