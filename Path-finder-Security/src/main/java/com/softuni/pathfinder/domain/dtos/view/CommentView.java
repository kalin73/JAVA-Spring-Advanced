package com.softuni.pathfinder.domain.dtos.view;

import java.time.format.DateTimeFormatter;

import com.softuni.pathfinder.domain.entities.Comment;

public class CommentView {
	private Long id;
	private String text;
	private String user;
	private String created;

	public CommentView(String text, String user, String created) {
		this.text = text;
		this.user = user;
		this.created = created;
	}

	public CommentView() {

	}

	public Long getId() {
		return id;
	}

	public CommentView setId(Long id) {
		this.id = id;
		return this;
	}

	public String getText() {
		return text;
	}

	public CommentView setText(String text) {
		this.text = text;
		return this;
	}

	public String getUser() {
		return user;
	}

	public CommentView setUser(String user) {
		this.user = user;
		return this;
	}

	public String getCreated() {
		return created;
	}

	public CommentView setCreated(String created) {
		this.created = created;
		return this;
	}

	public static CommentView mapToCommentView(Comment comment) {
		CommentView commentView = new CommentView(comment.getText(), comment.getUser().getFullName(),
				comment.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
		commentView.setId(comment.getId());

		return commentView;
	}

}
