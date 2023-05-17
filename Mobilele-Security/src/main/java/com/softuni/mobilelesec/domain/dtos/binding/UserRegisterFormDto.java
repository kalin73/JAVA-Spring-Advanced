package com.softuni.mobilelesec.domain.dtos.binding;

import com.softuni.mobilelesec.validations.matchingPasswords.PasswordMatch;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class UserRegisterFormDto {
	@NotNull
	@Size(min = 5, max = 20)
	private String username;

	@NotNull
	@Size(min = 5, max = 20)
	private String password;

	@NotNull
	@Size(min = 5, max = 20)
	private String confirmPassword;

	@NotNull
	@Size(min = 5, max = 20)
	private String firstName;

	@NotNull
	@Size(min = 5, max = 20)
	private String lastName;

	private String role;

	public UserRegisterFormDto() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
