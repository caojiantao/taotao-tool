package com.taotao.tool.dto.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddAlbumReq {

    @NotEmpty(message = "相册名称不能为空")
    private String name;

    private String description;
}
