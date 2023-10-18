package com.taotao.tool.lovenote.entity;

import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import lombok.Data;

@Data
public class LoveNoteUserDto {

    private LoveNoteUser user;
    private LoveNoteCp userCp;
    private LoveNoteUser userPartner;
}
