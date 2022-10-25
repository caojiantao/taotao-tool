package com.taotao.tool.dto;

import com.taotao.tool.model.File;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumFileDTO extends File {

    private Integer albumFileId;
}
