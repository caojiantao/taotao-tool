package com.taotao.tool.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AlbumPicPageReq extends BasePageReq {

    private Integer albumId;
}
