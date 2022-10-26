package com.taotao.tool.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao
 * @since 2022-10-25
 */
@Getter
@Setter
public class User extends BaseModel {

    private String username;

    private String password;
}
