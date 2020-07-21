package com.lsl.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
     */
    private static final String BASEPACKAGE = "com.lsl";

    @Bean
    public Docket createInstanceDocket() {
        Docket docket =
            new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(BASEPACKAGE))
                // .apis(RequestHandlerSelectors.basePackage("com.xxx"))
                .paths(PathSelectors.any()).build().apiInfo(createInstanceApiInfo());
        return docket;
    }

    private ApiInfo createInstanceApiInfo() {
        return new ApiInfoBuilder().title("commons").description("commons").version("1.0")
            .termsOfServiceUrl("https://github.com/610039525xyz")
            .contact(new Contact("lsl", "https://github.com/610039525xyz", "610039525xyz@163.com")).license("contact me")
            .licenseUrl("https://github.com/610039525xyz").build();
    }
}
