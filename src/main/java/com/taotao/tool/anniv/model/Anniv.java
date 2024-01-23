package com.taotao.tool.anniv.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author caojiantao
 * @since 2024-01-23
 */
@Getter
@Setter
public class Anniv {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String annivType;

    private LocalDate annivDate;

    private Boolean lunar;

    private String remark;
}
