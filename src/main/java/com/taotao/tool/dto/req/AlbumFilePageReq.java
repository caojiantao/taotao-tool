package com.taotao.tool.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumFilePageReq extends BasePageReq {

    private Integer albumId;
    private String fileType;
}
