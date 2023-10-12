package com.taotao.tool.spring.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("login")
public class LoginYml {

    private String passwordSalt;
    private String tokenSalt;
    private String domain;
}
