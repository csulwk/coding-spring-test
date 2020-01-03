package com.lwk.coding.config;

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
 *  Api注解可以用来标记当前Controller的功能;
 *  ApiOperation注解用来标记一个方法的作用;
 *  ApiImplicitParam注解用来描述一个参数, 如果有多个参数，则需要使用多个@ApiImplicitParam注解来描述;
 *  如果参数是一个对象，对于参数的描述也可以放在实体类中;
 * @author kai
 * @date 2020-01-03 23:37
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lwk.coding.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(creatApiInfo());
    }

    private ApiInfo creatApiInfo() {
        return new ApiInfoBuilder()
                .title("The  Restful API Of Coding-Spring-Test")
                .contact(new Contact("lwk", "https://github.com/csulwk/coding-spring-test.git", ""))
                .version("1.0")
                .description("SpringBoot集成Swagger2构建RESTful API")
                .build();
    }
}
