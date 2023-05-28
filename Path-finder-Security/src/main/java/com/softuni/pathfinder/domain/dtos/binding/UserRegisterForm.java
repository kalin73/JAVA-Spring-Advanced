package com.softuni.pathfinder.domain.dtos.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterForm {
	@NotNull
	@Size(min = 6, max = 20)
	private String username;

	@Size(min = 5, max = 20)
	private String fullName;

	@Email
	private String email;

	@NotNull
	private Integer age;

	@Size(min = 5, max = 20)
	private String password;

	@Size(min = 5, max = 20)
	private String confirmPassword;

	public UserRegisterForm() {

	}

	public String getFullName() {
		return fullName;
	}

	public UserRegisterForm setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserRegisterForm setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserRegisterForm setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserRegisterForm setEmail(String email) {
		this.email = email;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public UserRegisterForm setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public UserRegisterForm setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

}
