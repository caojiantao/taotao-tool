package com.taotao.tool.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao
 * @since 2022-08-30
 */
@Getter
@Setter
public class Album extends BaseModel {

    private Integer coverId;

    private String name;

    private String description;
}
