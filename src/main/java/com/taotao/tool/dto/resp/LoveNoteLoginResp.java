package com.taotao.tool.dto.resp;

import com.taotao.tool.lovenote.model.LoveNoteUser;
import lombok.Data;

@Data
public class LoveNoteLoginResp {

    private String openid;
    private LoveNoteUser user;
    private String token;
}
