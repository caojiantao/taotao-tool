package com.taotao.tool.carpool.entity;

import com.taotao.tool.carpool.constant.ECarpoolLineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarpoolLineListItem {

    private ECarpoolLineType type;

    private Driver driver;
    private Line line;
    private Seat seat;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Driver {
        private String avatar;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Line {
        private String from;
        private String to;
        private String time;
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
