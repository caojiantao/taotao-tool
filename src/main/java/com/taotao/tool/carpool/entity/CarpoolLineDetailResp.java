package com.taotao.tool.carpool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarpoolLineDetailResp {

    private Driver driver;
    private Car car;
    private Line line;
    private Seat seat;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Driver {
        private String avatar;
        private String nickname;
        private String phone;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Car {
        private String brand;
        private String no;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Line {
        private String time;
        private PositionDTO from;
        private PositionDTO to;
        private List<PositionDTO> pathwayList;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Seat {
        private Integer price;
        private String remark;
    }
}
