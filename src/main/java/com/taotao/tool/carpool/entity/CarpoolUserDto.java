package com.taotao.tool.carpool.entity;

import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.model.CarpoolLine;
import lombok.Data;

@Data
public class CarpoolUserDto {

    private String openid;
    private String nickname;

    private CarpoolDriver driver;
    private CarpoolLine line;
}
