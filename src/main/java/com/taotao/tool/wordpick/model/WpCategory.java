package com.taotao.tool.wordpick.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("wp_category")
public class WpCategory {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String icon;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
