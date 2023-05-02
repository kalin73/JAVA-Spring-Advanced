package com.softuni.errors.config;


import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class ErrorConfig {

	@Bean
	HandlerExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

		Properties properties = new Properties();

		properties.setProperty(NullPointerException.class.getSimpleName(), "npe");

		smer.setExceptionMappings(properties);

		return smer;
	}

}
