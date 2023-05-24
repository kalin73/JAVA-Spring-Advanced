package com.softuni.dbimages.model;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column(name = "content_type")
	private String contentType;

	@Lob
	@Column(length = Integer.MAX_VALUE)
	private byte[] data;

	public Long getId() {
		return id;
	}

	public FileEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public FileEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getContentType() {
		return contentType;
	}

	public FileEntity setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public byte[] getData() {
		return data;
	}

	public FileEntity setData(byte[] data) {
		this.data = data;
		return this;
	}
}
