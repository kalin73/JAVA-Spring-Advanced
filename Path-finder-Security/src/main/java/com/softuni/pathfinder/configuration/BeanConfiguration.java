package com.softuni.pathfinder.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.SessionScope;

import com.softuni.pathfinder.helpers.LoggedUser;
import com.softuni.pathfinder.repositories.UserRepository;
import com.softuni.pathfinder.services.PathfinderUserDetailsService;

@Configuration
public class BeanConfiguration {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@SessionScope
	LoggedUser loggedUser() {
		return new LoggedUser();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(matcher -> matcher
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/auth/login", "/auth/register", "/auth/login-error").anonymous()
                .requestMatchers("/users/profile").authenticated()
				.requestMatchers("/routes/add", "/routes", "/routes/*", "/api/**").permitAll())
				.formLogin(login -> login
						.loginPage("/auth/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureForwardUrl("/auth/login-error"))
                .logout(logout -> logout.logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true))
                .rememberMe(me -> me
						.key("someUniqueKey").tokenValiditySeconds(604800));

		return httpSecurity.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		return new PathfinderUserDetailsService(userRepository);
	}
}
