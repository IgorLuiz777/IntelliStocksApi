package br.com.intellistocks.api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public GroupedOpenApi customOpenAPI(SwaggerUiConfigParameters swaggerUiConfigParameters) {
        return GroupedOpenApi.builder()
                .group("produtos")
                .packagesToScan("br.com.intellistocks.api.controller")
                .build();
    }
}
