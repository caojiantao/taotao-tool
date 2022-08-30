package com.taotao.tool.dto.req;

import javax.validation.constraints.NotEmpty;

public class AddAlbumReq {

    @NotEmpty(message = "相册名称不能为空")
    private String name;

    private String desc;
}
