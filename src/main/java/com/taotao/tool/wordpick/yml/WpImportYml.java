package com.taotao.tool.wordpick.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("wordpick.import")
public class WpImportYml {

    private String token;
}
