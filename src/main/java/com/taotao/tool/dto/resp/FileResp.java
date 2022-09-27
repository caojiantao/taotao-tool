package com.taotao.tool.dto.resp;

import com.taotao.tool.enums.EFileType;
import com.taotao.tool.model.Pic;
import com.taotao.tool.model.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResp {

    private EFileType fileType;
    private Pic pic;
    private Video video;
}
