package com.taotao.tool.carpool.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
@TableName("carpool_line")
public class CarpoolLine {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String homePosition;

    private String pathwayPosition;

    private String workPosition;

    private LocalTime workTime;

    private LocalTime homeTime;

    private String replay;

    private Integer price;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
