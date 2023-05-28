package com.softuni.pathfinder.domain.dtos.model;

import com.softuni.pathfinder.domain.enums.RoleName;

public class RoleModel {
	private Long id;

	private RoleName role;

	public RoleModel() {

	}

	public Long getId() {
		return id;
	}

	public RoleModel setId(Long id) {
		this.id = id;
		return this;
	}

	public RoleName getRole() {
		return role;
	}

	public RoleModel setRole(RoleName role) {
		this.role = role;
		return this;
	}
}
