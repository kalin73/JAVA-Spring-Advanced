package com.softuni.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.softuni.security.model.enums.UserRoleEnum;
import com.softuni.security.repository.UserRepository;
import com.softuni.security.web.ApplicationUserDetailsService;

@Configuration
public class SecurityConfiguration {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
				.requestMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name())
				.requestMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).anyRequest().authenticated().and()
				.formLogin(login -> login.loginPage("/users/login")
						.usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
						.passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
						.defaultSuccessUrl("/", true).failureForwardUrl("/users/login-error"));

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		return new ApplicationUserDetailsService(userRepository);
	}
}
