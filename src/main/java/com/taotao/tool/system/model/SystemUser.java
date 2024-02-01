package com.taotao.tool.system.model;

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
 * @author caojiantao
 * @since 2024-02-01
 */
@Getter
@Setter
@TableName("system_user")
public class SystemUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickname;

    private String avatar;

    private String password;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
