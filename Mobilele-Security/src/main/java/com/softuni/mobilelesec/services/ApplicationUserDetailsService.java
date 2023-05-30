package com.softuni.mobilelesec.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.entities.UserRoleEntity;
import com.softuni.mobilelesec.repositories.UserRepository;

public class ApplicationUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public ApplicationUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(email).map(this::map)
				.orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " not found!"));
	}

	private UserDetails map(UserEntity userEntity) {
		return new User(userEntity.getEmail(), userEntity.getPassword(), extractAuthorities(userEntity));

	}

	private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
		return userEntity.getRoles().stream().map(this::mapRole).toList();
	}

	private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
		return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
	}

}
