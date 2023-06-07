package com.softuni.pathfindersec.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
	@Column
	private Boolean approved;

	@Column
	private LocalDateTime created;

	@Column(columnDefinition = "TEXT", name = "text_content")
	private String text;

	@ManyToOne
	private UserEntity user;

	@ManyToOne
	private Route route;

	public Comment() {

	}

	public Boolean getApproved() {
		return approved;
	}

	public Comment setApproved(Boolean approved) {
		this.approved = approved;
		return this;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public Comment setCreated(LocalDateTime created) {
		this.created = created;
		return this;
	}

	public String getText() {
		return text;
	}

	public Comment setText(String text) {
		this.text = text;
		return this;
	}

	public UserEntity getUser() {
		return user;
	}

	public Comment setUser(UserEntity user) {
		this.user = user;
		return this;
	}

	public Route getRoute() {
		return route;
	}

	public Comment setRoute(Route route) {
		this.route = route;
		return this;
	}

}
