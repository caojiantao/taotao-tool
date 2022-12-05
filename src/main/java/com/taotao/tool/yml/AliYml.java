package com.taotao.tool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("ali")
public class AliYml {

    private String key;
    private String secret;
}
