package com.softuni.security.web;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softuni.security.model.entity.UserEntity;
import com.softuni.security.model.entity.UserRoleEntity;
import com.softuni.security.repository.UserRepository;

public class ApplicationUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public ApplicationUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findUserByEmail(username).map(this::map)
				.orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
	}

	private UserDetails map(UserEntity userEntity) {
		return new User(userEntity.getEmail(), userEntity.getPassword(), extractAuthorities(userEntity));
	}

	private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
		return userEntity.getRoles().stream().map(this::mapRole).toList();
	}

	private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
		return new SimpleGrantedAuthority("ROLE_ " + userRoleEntity.getRole().name());
	}
}
