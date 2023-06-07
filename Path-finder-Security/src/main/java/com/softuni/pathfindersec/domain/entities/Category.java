package com.softuni.pathfindersec.domain.entities;

import com.softuni.pathfindersec.domain.enums.CategoryName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private CategoryName name;

	@Column(columnDefinition = "TEXT")
	private String description;

	public Category() {

	}

	public CategoryName getName() {
		return name;
	}

	public Category setName(CategoryName name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Category setDescription(String description) {
		this.description = description;
		return this;
	}

}
