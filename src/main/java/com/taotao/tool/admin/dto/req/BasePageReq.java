package com.taotao.tool.admin.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasePageReq {

    @NotNull(message = "页码不能为空")
    private Integer current;
    @NotNull(message = "分页大小不能为空")
    private Integer size;
}
