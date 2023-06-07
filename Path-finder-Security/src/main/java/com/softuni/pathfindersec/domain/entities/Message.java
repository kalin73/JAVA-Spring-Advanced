package com.softuni.pathfindersec.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
	@Column(name = "date_time")
	private LocalDateTime dateTime;

	@Column(name = "text_content")
	private String textContent;

	@ManyToOne
	private UserEntity author;

	@ManyToOne
	private UserEntity recipient;

	public Message() {

	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Message setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
		return this;
	}

	public String getTextContent() {
		return textContent;
	}

	public Message setTextContent(String textContent) {
		this.textContent = textContent;
		return this;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public Message setAuthor(UserEntity author) {
		this.author = author;
		return this;
	}

	public UserEntity getRecipient() {
		return recipient;
	}

	public Message setRecipient(UserEntity recipient) {
		this.recipient = recipient;
		return this;
	}

}
