package com.softuni.pathfinder.domain.entities;

import java.util.Set;

import com.softuni.pathfinder.domain.enums.Level;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private String email;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@Enumerated(EnumType.STRING)
	private Level level;

	@Column
	private String fullName;

	@Column
	private Integer age;

	public UserEntity() {

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

	public String getEmail() {
		return email;
	}

	public UserEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public UserEntity setRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public Level getLevel() {
		return level;
	}

	public UserEntity setLevel(Level level) {
		this.level = level;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public UserEntity setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public UserEntity setAge(Integer age) {
		this.age = age;
		return this;
	}

}
