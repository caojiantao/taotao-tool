package com.taotao.tool;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@MapperScan("com.taotao.tool.mapper")
@EnableConfigurationProperties
@EnableScheduling
@SpringBootApplication
public class TaotaoToolApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TaotaoToolApplication.class, args);
        log.info(context.getId() + " 启动成功！！");
    }
}
