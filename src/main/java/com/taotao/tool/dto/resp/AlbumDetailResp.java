package com.taotao.tool.dto.resp;

import com.taotao.tool.model.Album;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumDetailResp extends Album {

    private String coverFilename;
    private Long picNum = 0L;
    private Long videoNum = 0L;
}
