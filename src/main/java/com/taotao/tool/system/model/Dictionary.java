package com.taotao.tool.system.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao
 * @since 2023-09-19
 */
@Getter
@Setter
public class Dictionary extends BaseModel {

    private String itemKey;

    private String itemValue;

    private String mark;
}
