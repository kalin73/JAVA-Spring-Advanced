package com.softuni.mobilelesec.domain.dtos.model;

import java.util.Date;

public class BrandDto {

	private String name;

	private Date created;

	private Date modified;

	public BrandDto() {

	}

	public BrandDto(String name, Date created, Date modified) {
		this.name = name;
		this.created = created;
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public BrandDto setName(String name) {
		this.name = name;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public BrandDto setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public BrandDto setModified(Date modified) {
		this.modified = modified;
		return this;
	}
}
