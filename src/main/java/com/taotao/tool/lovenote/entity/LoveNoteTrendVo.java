package com.taotao.tool.lovenote.entity;

import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LoveNoteTrendVo {

    private Integer id;

    private String content;

    private String timeFmt;

    private List<LoveNoteTrendMediaVo> mediaList;

    private LoveNoteCp cp;
    private UserVo user;
    private UserVo partner;

    @Data
    public static class UserVo {
        private String nickname;
        private String avatarUrl;
    }
}
