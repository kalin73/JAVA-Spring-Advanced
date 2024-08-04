package org.example.mobileleoffers.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(new Info().description("This is the mobilele micro service.")
                .title("Mobilele offers API")
                .version("0.0.1")
                .contact(new Contact()
                        .name("Pesho")
                        .email("pesho@gmail.com")));


        return openAPI;
    }
}
