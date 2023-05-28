package com.softuni.pathfinder.helpers;

import java.util.Set;

import com.softuni.pathfinder.domain.dtos.model.RoleModel;
import com.softuni.pathfinder.domain.enums.RoleName;

public class LoggedUser {
	private Long id;

	private String username;

	private Set<RoleModel> roles;

	public LoggedUser() {

	}

	public Long getId() {
		return id;
	}

	public LoggedUser setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public LoggedUser setUsername(String username) {
		this.username = username;
		return this;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public LoggedUser setRoles(Set<RoleModel> roles) {
		this.roles = roles;
		return this;
	}

	public void clearFields() {
		this.id = null;
		this.username = null;
		this.roles = null;
	}

	public boolean isAdmin() {
		return this.roles.stream().anyMatch(role -> role.getRole().equals(RoleName.ADMIN));
	}
}
