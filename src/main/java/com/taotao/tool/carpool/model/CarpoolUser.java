package com.taotao.tool.carpool.model;

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
 * @since 2023-12-21
 */
@Getter
@Setter
@TableName("carpool_user")
public class CarpoolUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String avatar;

    private String nickname;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
