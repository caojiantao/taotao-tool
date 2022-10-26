package com.taotao.tool.model;

import com.taotao.tool.enums.EFileType;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author taotao
 * @since 2022-09-30
 */
@Getter
@Setter
public class File extends BaseModel {

    private Integer userId;

    private String filename;

    private String md5;

    private Long bytes;

    private EFileType fileType;

    private String mimeType;

    private String ext;
}
