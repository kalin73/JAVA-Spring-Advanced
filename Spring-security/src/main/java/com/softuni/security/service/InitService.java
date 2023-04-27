package com.softuni.security.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softuni.security.model.entity.UserEntity;
import com.softuni.security.model.entity.UserRoleEntity;
import com.softuni.security.model.enums.UserRoleEnum;
import com.softuni.security.repository.UserRepository;
import com.softuni.security.repository.UserRoleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class InitService {
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	public InitService(UserRepository userRepository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void init() {
		initRoles();
		initUsers();
	}

	public void initRoles() {
		if (userRoleRepository.count() == 0) {
			var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
			var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

			userRoleRepository.save(moderatorRole);
			userRoleRepository.save(adminRole);
		}
	}

	public void initUsers() {
		if (userRepository.count() == 0) {
			initAdmin();
			initModerator();
			initNormalUser();

		}
	}

	private void initAdmin() {
		var adminUser = new UserEntity().setEmail("admin@example.com").setFirstName("Admin").setLastName("Adminov")
				.setPassword(this.passwordEncoder.encode("topsecret")).setRoles(userRoleRepository.findAll());

		userRepository.save(adminUser);
	}

	private void initModerator() {
		var moderatorRole = userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR)
				.orElseThrow(NoSuchElementException::new);

		var moderatorUser = new UserEntity().setEmail("moderator@example.com").setFirstName("Moderator")
				.setLastName("Moderatorov").setPassword(this.passwordEncoder.encode("topsecret"))
				.addRole(moderatorRole);

		userRepository.save(moderatorUser);
	}

	private void initNormalUser() {
		var normalUser = new UserEntity().setEmail("user@example.com").setFirstName("User").setLastName("Userov")
				.setPassword(this.passwordEncoder.encode("topsecret"));

		userRepository.save(normalUser);
	}

}
