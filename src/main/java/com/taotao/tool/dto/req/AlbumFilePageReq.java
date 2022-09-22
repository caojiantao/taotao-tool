package com.taotao.tool.dto.req;

import com.taotao.tool.enums.EFileType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumFilePageReq extends BasePageReq {

    private Integer albumId;
    private EFileType fileType;
}
