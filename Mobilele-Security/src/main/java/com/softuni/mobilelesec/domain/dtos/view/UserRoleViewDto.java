package com.softuni.mobilelesec.domain.dtos.view;

import com.softuni.mobilelesec.domain.enums.UserRoleEnum;

public class UserRoleViewDto {
	private UserRoleEnum role;

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}
}
