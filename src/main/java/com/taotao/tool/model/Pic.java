package com.taotao.tool.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
@Getter
@Setter
public class Pic extends BaseModel {

    private String filename;

    private String md5;

    private Long bytes;

    private String contentType;
}
