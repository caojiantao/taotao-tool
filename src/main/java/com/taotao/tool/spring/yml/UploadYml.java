package com.taotao.tool.spring.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadYml {

    private Image image;

    @Data
    public static class Image {
        private String path;
        private Double quality;
    }
}
