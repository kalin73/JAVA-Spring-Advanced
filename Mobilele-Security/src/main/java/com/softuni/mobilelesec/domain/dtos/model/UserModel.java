package com.softuni.mobilelesec.domain.dtos.model;

import java.util.Date;
import java.util.List;

public class UserModel {
	private String id;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String imageUrl;

	private Boolean isActive;

	private List<UserRoleModel> role;

	private Date created;

	private Date modified;

	public UserModel() {

	}

	public String getId() {
		return id;
	}

	public UserModel setId(String id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserModel setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserModel setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserModel setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserModel setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public UserModel setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public UserModel setIsActive(Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public List<UserRoleModel> getRole() {
		return role;
	}

	public UserModel setRole(List<UserRoleModel> role) {
		this.role = role;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public UserModel setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public UserModel setModified(Date modified) {
		this.modified = modified;
		return this;
	}
	
	public boolean isValid() {
		return this.id != null;
	}
}
