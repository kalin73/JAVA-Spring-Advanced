package com.softuni.pathfinder.domain.dtos.view;

import com.softuni.pathfinder.domain.enums.Level;

public class UserProfileModel {
	private String username;

	private Level level;

	private String fullName;

	private Integer age;

	public UserProfileModel() {

	}

	public String getUsername() {
		return username;
	}

	public UserProfileModel setUsername(String username) {
		this.username = username;
		return this;
	}

	public Level getLevel() {
		return level;
	}

	public UserProfileModel setLevel(Level level) {
		this.level = level;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public UserProfileModel setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public UserProfileModel setAge(Integer age) {
		this.age = age;
		return this;
	}
}
