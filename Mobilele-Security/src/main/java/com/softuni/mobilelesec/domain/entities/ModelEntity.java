package com.softuni.mobilelesec.domain.entities;

import java.util.Date;

import com.softuni.mobilelesec.domain.enums.ModelCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private ModelCategory category;

	@Column
	private String imageUrl;

	@Column(name = "start_year")
	private Integer startYear;

	@Column(name = "end_year")
	private Integer endYear;

	@Column
	private Date created;

	@Column
	private Date modified;

	@ManyToOne
	private BrandEntity brand;

	public ModelEntity() {

	}

	public ModelEntity(String name, ModelCategory category, String imageUrl, Integer startYear, Integer endYear, Date created,
			Date modified, BrandEntity brand) {
		this.name = name;
		this.category = category;
		this.imageUrl = imageUrl;
		this.startYear = startYear;
		this.endYear = endYear;
		this.created = created;
		this.modified = modified;
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ModelCategory getCategory() {
		return category;
	}

	public void setCategory(ModelCategory category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

}
