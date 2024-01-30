package com.taotao.tool.todo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author caojiantao
 * @since 2024-01-30
 */
@Getter
@Setter
public class Todo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer groupId;

    private String title;

    private String content;

    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
