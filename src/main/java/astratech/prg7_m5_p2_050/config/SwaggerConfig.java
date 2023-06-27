package astratech.prg7_m5_p2_050.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("astratech.prg7_m5_p2_050.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Swagger 0320210050",
                "Praktikum Swagger Manajemen Informatika: Kumpulan API Praktikum M5_2 Helm",
                "1.0",
                "Terms of Service",
                "Noviani Putri",
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
        return apiInfo;
    }



}