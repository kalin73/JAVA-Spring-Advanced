package com.softuni.mobilelesec.domain.entities;

import com.softuni.mobilelesec.domain.enums.UserRoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	public UserRoleEntity() {

	}

	public UserRoleEntity(UserRoleEnum role) {
		this.role = role;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public UserRoleEntity setRole(UserRoleEnum role) {
		this.role = role;
		return this;
	}
}
