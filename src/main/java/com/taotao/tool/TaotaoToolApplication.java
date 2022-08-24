package com.taotao.tool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.taotao.tool.mapper")
@EnableConfigurationProperties
@SpringBootApplication
public class TaotaoToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoToolApplication.class, args);
    }

}
