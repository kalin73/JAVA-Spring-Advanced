package com.softuni.mobilelesec.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softuni.mobilelesec.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilelesec.domain.dtos.model.UserModel;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.repositories.UserRepository;
import com.softuni.mobilelesec.repositories.UserRoleRepository;

@Service
public class UserService extends DataBaseInitService {
	private final UserRepository userRepository;
	private final UserRoleService userRoleService;
	private final UserRoleRepository userRoleRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final String defaultAdminPass;

	public UserService(UserRepository userRepository, ModelMapper modelMapper, UserRoleService userRoleService,
			PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository,
			@Value("${mobilele.admin.defaultpass}") String defaultAdminPass) {
		super(userRoleService);
		this.userRepository = userRepository;
		this.userRoleService = userRoleService;
		this.userRoleRepository = userRoleRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.defaultAdminPass = defaultAdminPass;
	}

	public void dbInit() {
		if (!isDbInit()) {
			UserEntity admin = new UserEntity().setFirstName("Admin").setLastName("Adminov")
					.setEmail("admin@example.com").setRoles(userRoleRepository.findAll())
					.setPassword(passwordEncoder.encode(defaultAdminPass));

			userRepository.save(admin);
		}

	}

	public boolean isDbInit() {
		return this.userRepository.count() > 0;
	}

	public UserModel registerUser(UserRegisterFormDto userRegister) {
		final UserModel userModel = modelMapper.map(userRegister, UserModel.class);

		userModel.setRole(!isDbInit() ? this.userRoleService.findAllRoles()
				: List.of(this.userRoleService.findRoleByName("USER")));

		UserEntity userToSave = this.modelMapper.map(userModel, UserEntity.class);
		userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));

		return this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
	}

}
