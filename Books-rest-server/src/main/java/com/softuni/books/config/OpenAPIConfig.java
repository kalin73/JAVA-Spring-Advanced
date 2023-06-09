package com.softuni.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(new Info().title("Our SoftUni book API")
				.contact(new Contact().email("students@softuni.bg").name("The Students"))
				.description("Small API for books/authors description."));
	}
}
