package com.fx.shop.config;

import com.sineyun.commons.base.TokenInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean enable;

    @Value("${swagger.application.name}")
    private String applicationName;

    @Value("${swagger.application.version}")
    private String applicationVersion;

    @Value("${swagger.application.description}")
    private String applicationDescription;

    @Value("${swagger.application.group01}")
    private String applicationGroup01;

    @Value("${swagger.application.group02}")
    private String applicationGroup02;

    @Value("${swagger.application.selector}")
    private String selector;

    @Value("${swagger.application.pathMapping}")
    private String pathMapping;

    @Bean
    public Docket createRestApiDev() {

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .ignoredParameterTypes(TokenInfo.class)
                .groupName(applicationGroup01)
                .select()
                .apis(RequestHandlerSelectors.basePackage(selector))
                .paths(PathSelectors.any())
                .build()
                //接口信息
                .apiInfo(apiInfo())
                //全局参数
//                .globalOperationParameters(globalOperationParameters())
                //全站统一参数token
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
                ;
    }

    @Bean
    public Docket createRestApiProd() {

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .pathMapping(pathMapping)
                .ignoredParameterTypes(TokenInfo.class)
                .groupName(applicationGroup02)
                .select()
                .apis(RequestHandlerSelectors.basePackage(selector))
                .paths(PathSelectors.any())
                .build()
                //接口信息
                .apiInfo(apiInfo())
                //全局参数
//                .globalOperationParameters(globalOperationParameters())
                //全站统一参数token
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
                ;
    }

    /**
     * api信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationDescription)
                // 作者信息
                .contact(new Contact("sineyun", "https://www.3yicloud.com", "service@3yicloud.com"))
                .version(applicationVersion)
                .build();
    }


    /**
     * 添加全局参数
     *
     * @return
     */
    private List<Parameter> globalOperationParameters() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();

        parameters.add(parameterBuilder
                .name("Authorization")
                .description("认证token，Bearer模式")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return parameters;
    }


    /**
     * 添加全局授权参数
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> arrayList = new ArrayList();
        arrayList.add(new ApiKey("Authorization", "Authorization", "header"));
        return arrayList;
    }

    /**
     * 授权地址
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!login).*$")).build());
        return securityContexts;
    }

    /**
     * 默认授权
     *
     * @return
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


}
