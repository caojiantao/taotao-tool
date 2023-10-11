package com.taotao.tool.lovenote.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Getter
@Setter
@TableName("love_note_user")
public class LoveNoteUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String nickname;

    private Integer gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
