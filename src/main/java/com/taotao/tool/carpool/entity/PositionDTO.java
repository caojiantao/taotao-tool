package com.taotao.tool.carpool.entity;

import lombok.Data;

@Data
public class PositionDTO {

    private String address;
    private String city;
    private String district;
    private Double latitude;
    private Double longitude;
    private String name;
    private String province;
}
