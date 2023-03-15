package com.mdd.admin.config;

import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
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
        return new ApiInfoBuilder()
                .title("LikeAdmin【后台】接口文档")
                .description("likeadmin快速开发管理后台")
                .version(GlobalConfig.version)
                .build();
    }

}
