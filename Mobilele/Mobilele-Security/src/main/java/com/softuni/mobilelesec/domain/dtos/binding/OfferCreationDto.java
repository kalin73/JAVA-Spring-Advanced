package com.softuni.mobilelesec.domain.dtos.binding;

import java.math.BigDecimal;
import java.util.UUID;

import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.enums.Engine;
import com.softuni.mobilelesec.domain.enums.Transmission;

public class OfferCreationDto {
	private String model;

	private BigDecimal price;

	private Engine engine;

	private Transmission transmission;

	private Integer year;

	private Integer mileage;

	private String description;

	private String imageUrl;

	public OfferCreationDto() {

	}

	public OfferCreationDto(String model, BigDecimal price, Engine engine, Transmission transmission, Integer year,
			Integer mileage, String description, String imageUrl) {
		this.model = model;
		this.price = price;
		this.engine = engine;
		this.transmission = transmission;
		this.year = year;
		this.mileage = mileage;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public static OfferEntity mapToEntity(OfferCreationDto creationDto, UserEntity seller) {
		OfferEntity offer = new OfferEntity();
		offer.setEngine(creationDto.getEngine());
		offer.setDescription(creationDto.getDescription());
		offer.setMileage(creationDto.getMileage());
		offer.setImageUrl(creationDto.getImageUrl());
		offer.setPrice(creationDto.getPrice());
		offer.setTransmission(creationDto.getTransmission());
		offer.setYear(creationDto.getYear());
		offer.setSeller(seller);
		offer.setOfferId(UUID.randomUUID());

		return offer;

	}

}
