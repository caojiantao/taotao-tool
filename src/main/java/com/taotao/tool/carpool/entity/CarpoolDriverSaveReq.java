package com.taotao.tool.carpool.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CarpoolDriverSaveReq {

    @NotEmpty
    private String openid;

    @NotEmpty
    private String realName;

    @NotEmpty
    private String idCard;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String driverLicense;

    @NotEmpty
    private String carNo;

    @NotEmpty
    private String carPermit;
}
