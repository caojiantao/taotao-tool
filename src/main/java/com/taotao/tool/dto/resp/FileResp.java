package com.taotao.tool.dto.resp;

import com.taotao.tool.enums.EFileType;
import com.taotao.tool.model.Pic;
import com.taotao.tool.model.Video;
import lombok.Data;

@Data
public class FileResp {

    private EFileType fileType;
    private Pic pic;
    private Video video;
}
