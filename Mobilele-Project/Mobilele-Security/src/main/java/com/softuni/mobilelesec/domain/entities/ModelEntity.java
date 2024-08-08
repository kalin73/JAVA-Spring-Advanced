package com.softuni.mobilelesec.domain.entities;

import jakarta.persistence.*;

import java.util.Date;

import com.softuni.mobilelesec.domain.enums.ModelCategory;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private ModelCategory category;

	@Column
	private String imageUrl;

	@Column
	private Integer startYear;

	@Column
	private Integer endYear;

	@Column
	private Date created;

	@Column
	private Date modified;

	@ManyToOne
	private BrandEntity brand;

	public ModelEntity() {

	}

	public ModelEntity(ModelCategory category) {
		this.category = category;

	}

	public String getName() {
		return name;
	}

	public ModelEntity setName(String name) {
		this.name = name;
		return this;
	}

	public ModelCategory getCategory() {
		return category;
	}

	public ModelEntity setCategory(ModelCategory category) {
		this.category = category;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public ModelEntity setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public ModelEntity setStartYear(Integer startYear) {
		this.startYear = startYear;
		return this;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public ModelEntity setEndYear(Integer endYear) {
		this.endYear = endYear;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public ModelEntity setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public ModelEntity setModified(Date modified) {
		this.modified = modified;
		return this;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public ModelEntity setBrand(BrandEntity brand) {
		this.brand = brand;
		return this;
	}
}
