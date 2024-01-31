package com.taotao.tool.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDayGroupDTO {

    private String groupTitle;
    private List<Item> itemList;

    @Data
    public static class Item {
        private Integer id;
        private Integer groupId;
        private String title;
        private String content;
        private Integer state;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
    }
}
