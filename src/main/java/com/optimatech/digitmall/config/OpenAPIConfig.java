package com.optimatech.digitmall.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {

    //link docs:   http://localhost:6789/swagger-ui/index.html#/
    @Value("${optimatech.openapi.dev-url}")
    private String devUrl;

    @Value("${optimatech.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url(devUrl)
                .description ("Server URL in Development environment");

        Server prodServer = new Server()
                .url(prodUrl)
                .description("Server URL in Production environment");

        Contact contact = new Contact()
                .email("nguyenmanhcongptit@gmail.com")
                .name("OptimaTech - Việt Nam")
                .url("https://www.facebook.com/bacong.nguyen.549");


        License chLicense = new License()
                .name("Verificate by VNLicense");

        Info info = new Info()
                .title("Tutorial Management Doccumentaions DigitMall Web Application API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://zalo.me/g/jkjxkt420")
                .license(chLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
