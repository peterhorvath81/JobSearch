package com.bredex.jobsearch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger configuration class
 */

@Configuration
public class SpringFoxConfig {

    /**
     * API documentation configuration
     * @return docket wit the configuration for Swagger UI
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bredex.jobsearch"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    /**
     * Meta information for Swagger UI
     * @return apiInfo
     */
    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Jobsearch REST API",
                "API Documentation for JobSearch Application",
                "1.0",
                "N/A",
                new Contact("Peter Horvath", " ",
                        "horvathpeterf@hotmail.com"),
                " ",
                " ", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }


}
