package com.taotao.tool.dto.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileReq {

    private List<FileItem> fileItems;

    @Data
    public static class FileItem {
        private MultipartFile file;
        private FileItemExt fileExt;
    }

    @Data
    public static class FileItemExt {
        private MultipartFile coverFile;
        private Integer second;
    }
}
