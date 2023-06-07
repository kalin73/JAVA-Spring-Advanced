package com.softuni.pathfindersec.domain.dtos.model;

import java.time.LocalDateTime;

public class CommentModel {
	private Long id;

	private Boolean approved;

	private LocalDateTime created;

	private String text;

	private UserModel user;

	private RouteModel route;

	public CommentModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public RouteModel getRoute() {
		return route;
	}

	public void setRoute(RouteModel route) {
		this.route = route;
	}

}
