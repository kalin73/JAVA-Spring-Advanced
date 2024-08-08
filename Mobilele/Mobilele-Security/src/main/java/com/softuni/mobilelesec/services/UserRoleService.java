package com.softuni.mobilelesec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.mobilelesec.domain.dtos.model.UserRoleModel;
import com.softuni.mobilelesec.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilelesec.domain.entities.UserRoleEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.UserRoleRepository;

@Service
public class UserRoleService {
	private final UserRoleRepository userRoleRepository;
	private final ModelMapper modelMapper;

	public UserRoleService(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
		this.userRoleRepository = userRoleRepository;
		this.modelMapper = modelMapper;
		this.dbInit();
	}

	public void dbInit() {
		if (!isDbInit()) {
			List<UserRoleEntity> roles = new ArrayList<>();
			roles.add(new UserRoleEntity().setRole(UserRoleEnum.USER));
			roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));

			this.userRoleRepository.saveAllAndFlush(roles);
		}
	}

	public boolean isDbInit() {
		return this.userRoleRepository.count() > 0;
	}

	public List<UserRoleViewDto> getAll() {
		return this.userRoleRepository.findAll().stream().map(role -> modelMapper.map(role, UserRoleViewDto.class))
				.toList();
	}

	public List<UserRoleModel> findAllRoles() {
		return this.userRoleRepository.findAll().stream().map(role -> modelMapper.map(role, UserRoleModel.class))
				.toList();
	}


	public UserRoleModel findRoleByName(String role) {
		return this.modelMapper.map(this.userRoleRepository.findByRole(role).orElseThrow(NoSuchElementException::new),
				UserRoleModel.class);
	}
}
