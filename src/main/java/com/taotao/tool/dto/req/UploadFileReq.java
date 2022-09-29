package com.taotao.tool.dto.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileReq {

    private List<FileItem> fileItems;

    @Data
    private static class FileItem {
        private MultipartFile file;

        private MultipartFile videoCover;
        private Integer videoSeconds;
    }
}
