package com.taotao.tool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("upload")
public class UploadYml {

    private ImageYml image;
    private VideoYml video;
}
