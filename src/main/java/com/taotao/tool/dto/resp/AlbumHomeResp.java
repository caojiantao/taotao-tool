package com.taotao.tool.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumHomeResp {

    private Long albumNum;
    private Long imageNum;
    private Long videoNum;
}
