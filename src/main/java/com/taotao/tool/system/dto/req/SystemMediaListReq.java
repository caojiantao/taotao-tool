package com.taotao.tool.system.dto.req;

import lombok.Data;

@Data
public class SystemMediaListReq extends BasePageReq {

    private String bucket;
    private String filename;
}
