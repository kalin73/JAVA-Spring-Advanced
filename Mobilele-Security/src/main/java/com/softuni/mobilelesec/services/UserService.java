package com.softuni.mobilelesec.services;

import java.util.List;

import org.modelmapper.ModelMapper;
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
	private final EmailService emailService;

	public UserService(UserRepository userRepository, ModelMapper modelMapper, UserRoleService userRoleService,
			PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, EmailService emailService) {
		super(userRoleService);
		this.userRepository = userRepository;
		this.userRoleService = userRoleService;
		this.userRoleRepository = userRoleRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public void dbInit() {
		if (!isDbInit()) {
			UserEntity admin = new UserEntity().setFirstName("Admin").setLastName("Adminov")
					.setEmail("admin@example.com").setRoles(userRoleRepository.findAll())
					.setPassword(passwordEncoder.encode("topsecret"));

			userRepository.save(admin);
		}

	}

	public boolean isDbInit() {
		return this.userRepository.count() > 0;
	}

	public UserModel registerUser1(UserRegisterFormDto userRegister) {
		final UserModel userModel = modelMapper.map(userRegister, UserModel.class);

		userModel.setRole(!isDbInit() ? this.userRoleService.findAllRoles()
				: List.of(this.userRoleService.findRoleByName("USER")));

		UserEntity userToSave = this.modelMapper.map(userModel, UserEntity.class);
		userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));

		return this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
	}

	public void registerUser(UserRegisterFormDto userRegister) {
		UserEntity userToSave = new UserEntity().setFirstName(userRegister.getFirstName())
				.setLastName(userRegister.getLastName()).setEmail(userRegister.getEmail())
				.setPassword(passwordEncoder.encode(userRegister.getPassword()));

		this.userRepository.save(userToSave);

		this.emailService.sendRegistrationEmail(userToSave.getEmail(),
				userToSave.getFirstName() + " " + userToSave.getLastName());
	}

}
