package com.taotao.tool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("ali")
public class AliYml {

    private String key;
    private String secret;

    private String domain;
    private List<String> rrList;
}
