package com.org.vetconnect.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI vetConnectApiConfiguration() {

        final String securitySchemaName = "bearerAuth";
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("VetConnect API")
                        .description("VetConnect application REST API documentation")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("VetConnect wiki Documentation")
                        .url("https://vetconnect-platform.wiki.github.io/docs"));


        openApi
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemaName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemaName,
                                new SecurityScheme()
                                        .name(securitySchemaName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
        return openApi;
    }
}
