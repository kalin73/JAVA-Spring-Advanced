package com.softuni.pathfindersec.services;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.pathfindersec.domain.dtos.model.RoleModel;
import com.softuni.pathfindersec.domain.enums.RoleName;
import com.softuni.pathfindersec.repositories.RoleRepository;

@Service
public class RoleService {
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;

	public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;

	}

	public Set<RoleModel> findAllRoles() {
		return this.roleRepository.findAll().stream().map(role -> modelMapper.map(role, RoleModel.class))
				.collect(Collectors.toSet());
	}

	public RoleModel findRoleByName(String name) {
		return this.modelMapper.map(
				this.roleRepository.findByRole(RoleName.valueOf(name)).orElseThrow(NoSuchElementException::new),
				RoleModel.class);
	}

}
