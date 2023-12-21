package com.taotao.tool.spring.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("carpool")
public class CarpoolYml {

    private String appId;
    private String appSecret;

    private String tokenSalt;
}
