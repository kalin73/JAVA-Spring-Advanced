package com.softuni.mobilelesec.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private Date created;

	@Column
	private Date modified;

	public BrandEntity() {

	}

	public BrandEntity(String name, Date created, Date modified) {
		this.name = name;
		this.created = created;
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public BrandEntity setName(String name) {
		this.name = name;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public BrandEntity setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public BrandEntity setModified(Date modified) {
		this.modified = modified;
		return this;
	}
}
