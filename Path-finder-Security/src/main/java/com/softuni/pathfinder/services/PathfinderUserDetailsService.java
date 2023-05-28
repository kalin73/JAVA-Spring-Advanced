package com.softuni.pathfinder.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softuni.pathfinder.domain.entities.UserEntity;
import com.softuni.pathfinder.repositories.UserRepository;

public class PathfinderUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public PathfinderUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

		return new User(
				user.getUsername(),
				user.getPassword(),
				user.getRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
						.toList());
	}

}
