package com.taotao.tool.dto.resp;

import com.taotao.tool.model.Album;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AlbumResp extends Album {

    private String coverFilename;
}
