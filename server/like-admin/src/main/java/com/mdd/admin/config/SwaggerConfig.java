package com.mdd.admin.config;

import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${like.swagger.enabled}")
    private boolean enabled;

    @Value("${like.swagger.pathMapping}")
    private String pathMapping;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mdd.admin.controller"))
                .build()
                .pathMapping(pathMapping);
    }

    private ApiInfo apiInfo(){
        String author = "FZR";
        String url = "https://gitee.com/likeadmin/likeadmin_java";
        String email = "tinyants@163.com";

        return new ApiInfoBuilder()
                .title("LikeAdmin【后台】接口文档")
                .description("likeadmin是一套使用流行的技术栈的快速开发管理后台")
                .version(GlobalConfig.version)
                .contact(new Contact(author, url, email))
                .build();
    }

}
