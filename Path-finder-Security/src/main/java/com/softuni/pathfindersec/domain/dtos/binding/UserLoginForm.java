package com.softuni.pathfindersec.domain.dtos.binding;

import com.softuni.pathfindersec.validations.userExists.ValidateLoginUser;

import jakarta.validation.constraints.NotNull;

@ValidateLoginUser
public class UserLoginForm {

	@NotNull
	private String username;

	@NotNull
	private String password;

	public UserLoginForm() {

	}

	public String getUsername() {
		return username;
	}

	public UserLoginForm setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserLoginForm setPassword(String password) {
		this.password = password;
		return this;
	}

}
