package com.blog.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sunchao
 * @since 2019/7/26 10:15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static Tag firstTag = new Tag("ArticleController", "Article信息管理");

    private static Tag[] tags = {
            new Tag("ArticleController", "Article信息管理"),
            new Tag("UserController", "User信息管理"),
            new Tag("CommunityController", "Community信息管理"),
            new Tag("EvaluationController", "Evaluation信息管理"),
            new Tag("EReplyController", "EReplyController")
    };

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .tags(firstTag, tags)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.blog"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Blog服务接口API")
                .description("提供Blog服务的接口API说明")
                .termsOfServiceUrl("")
                .build();
    }
}
