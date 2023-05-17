package com.softuni.mobilelesec.domain.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "is_active")
	private Boolean isActive;

	@OneToMany
	private List<UserRoleEntity> roles;

	@Column
	private Date created;

	@Column
	private Date modified;

	public UserEntity() {

	}

	public UserEntity(String username, String password, String firstName, String lastName, String imageUrl,
			Boolean isActive, List<UserRoleEntity> roles, Date created, Date modified) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
		this.isActive = isActive;
		this.roles = roles;
		this.created = created;
		this.modified = modified;
	}

	public String getUsername() {
		return username;
	}

	public UserEntity setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserEntity setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserEntity setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public UserEntity setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public UserEntity setIsActive(Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public List<UserRoleEntity> getRoles() {
		return roles;
	}

	public UserEntity setRoles(List<UserRoleEntity> roles) {
		this.roles = roles;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public UserEntity setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public UserEntity setModified(Date modified) {
		this.modified = modified;
		return this;
	}
}
