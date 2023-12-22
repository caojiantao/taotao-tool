package com.taotao.tool.carpool.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CarpoolUserRegisterReq {

    @NotEmpty
    private String code;
    @NotEmpty
    private String avatar;
    @NotEmpty
    private String nickname;
}
