package com.softuni.mobilelesec.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.softuni.mobilelesec.repositories.UserRepository;
import com.softuni.mobilelesec.services.ApplicationUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// defines which pages will be authorized
		http.authorizeHttpRequests(matcher -> matcher
				// allow access to all static files (images, CSS, js)
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				// the URL-s below are available for all users - logged in and anonymous
				.requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
				.requestMatchers("/offers/*").authenticated()
				.requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll())
				.formLogin(login -> login.loginPage("/users/login")
						// the names of the user name, password input fields in the custom login form
						.usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
						.passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
						// where do we go after login
						.defaultSuccessUrl("/", true).failureForwardUrl("/users/login-error"))
				// go to homepage after logout
				.logout(logout -> logout.logoutUrl("/users/logout").logoutSuccessUrl("/").invalidateHttpSession(true));

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