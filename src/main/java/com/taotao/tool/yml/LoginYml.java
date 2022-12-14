package com.taotao.tool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("login")
public class LoginYml {

    private String redirectUrl;
    private String passwordSalt;
    private String tokenSalt;
}
