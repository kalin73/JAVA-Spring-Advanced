package com.softuni.mobilelesec.domain.dtos.model;

import java.math.BigDecimal;
import java.util.Date;

import com.softuni.mobilelesec.domain.entities.ModelEntity;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.enums.Engine;
import com.softuni.mobilelesec.domain.enums.Transmission;

public class OfferDto {

	private String description;

	private Engine engine;

	private String mileage;

	private BigDecimal price;

	private Transmission transmission;

	private String year;

	private Date created;

	private Date modified;

	private ModelEntity model;

	private UserEntity seller;

	public OfferDto() {

	}

	public OfferDto(String description, Engine engine, String mileage, BigDecimal price, Transmission transmission,
			String year, Date created, Date modified, ModelEntity model, UserEntity seller) {
		this.description = description;
		this.engine = engine;
		this.mileage = mileage;
		this.price = price;
		this.transmission = transmission;
		this.year = year;
		this.created = created;
		this.modified = modified;
		this.model = model;
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public OfferDto setDescription(String description) {
		this.description = description;
		return this;
	}

	public Engine getEngine() {
		return engine;
	}

	public OfferDto setEngine(Engine engine) {
		this.engine = engine;
		return this;
	}

	public String getMileage() {
		return mileage;
	}

	public OfferDto setMileage(String mileage) {
		this.mileage = mileage;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public OfferDto setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public OfferDto setTransmission(Transmission transmission) {
		this.transmission = transmission;
		return this;
	}

	public String getYear() {
		return year;
	}

	public OfferDto setYear(String year) {
		this.year = year;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public OfferDto setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getModified() {
		return modified;
	}

	public OfferDto setModified(Date modified) {
		this.modified = modified;
		return this;
	}

	public ModelEntity getModel() {
		return model;
	}

	public OfferDto setModel(ModelEntity model) {
		this.model = model;
		return this;
	}

	public UserEntity getSeller() {
		return seller;
	}

	public OfferDto setSeller(UserEntity seller) {
		this.seller = seller;
		return this;
	}
}
