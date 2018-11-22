package com.xinwei;

import com.xinwei.config.shiro.auto.annotation.EnableShiroWebSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.Target;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@MapperScan("com.xinwei.dao")
@EnableShiroWebSupport
@ComponentScan("com.xinwei")
public class WebApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }


}
