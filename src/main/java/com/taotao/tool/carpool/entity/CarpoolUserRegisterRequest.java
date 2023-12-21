package com.taotao.tool.carpool.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CarpoolUserRegisterRequest {

    @NotEmpty
    private String code;
    @NotEmpty
    private String avatar;
    @NotEmpty
    private String nickname;
}
