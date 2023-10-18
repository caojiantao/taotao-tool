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
    private LoveNoteUser user;
    private LoveNoteUser partner;
}
