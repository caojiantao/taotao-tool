package com.taotao.tool.lovenote.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoveNoteUserRegisterRequest {

    @NotEmpty
    private String code;
    @NotEmpty
    private String avatarUrl;
    @NotEmpty
    private String nickname;
    @NotNull
    private Integer gender;
}
