package com.hxkj.admin;

import com.github.yulichang.injector.MPJSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.hxkj"})
@MapperScan(basePackages = {"com.hxkj.*.mapper"})
@SpringBootApplication(exclude = {MPJSqlInjector.class})
public class LikeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeAdminApplication.class, args);
    }

}
