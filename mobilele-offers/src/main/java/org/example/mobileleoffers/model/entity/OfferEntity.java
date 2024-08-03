package org.example.mobileleoffers.model.entity;

import org.example.mobileleoffers.model.enums.EngineTypeEnum;
import org.example.mobileleoffers.model.enums.Transmission;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
	@Column
	private String description;

	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID offerId;

	@Enumerated(EnumType.STRING)
	private EngineTypeEnum engine;

	@Column
	private String imageUrl;

	@Column
	private Integer mileage;

	@Column
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private Transmission transmission;

	@Column
	private Integer year;

	@ManyToOne
	private ModelEntity model;

	@ManyToOne
	private UserEntity seller;

	public String getDescription() {
		return description;
	}

	public OfferEntity setDescription(String description) {
		this.description = description;
		return this;
	}

	public EngineTypeEnum getEngine() {
		return engine;
	}

	public OfferEntity setEngine(EngineTypeEnum engine) {
		this.engine = engine;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public OfferEntity setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Integer getMileage() {
		return mileage;
	}

	public OfferEntity setMileage(Integer mileage) {
		this.mileage = mileage;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public OfferEntity setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public OfferEntity setTransmission(Transmission transmission) {
		this.transmission = transmission;
		return this;
	}

	public Integer getYear() {
		return year;
	}

	public OfferEntity setYear(Integer year) {
		this.year = year;
		return this;
	}

	public ModelEntity getModel() {
		return model;
	}

	public OfferEntity setModel(ModelEntity model) {
		this.model = model;
		return this;
	}

	public UserEntity getSeller() {
		return seller;
	}

	public OfferEntity setSeller(UserEntity seller) {
		this.seller = seller;
		return this;
	}

	public UUID getOfferId() {
		return offerId;
	}

	public OfferEntity setOfferId(UUID offerId) {
		this.offerId = offerId;
		return this;
	}
}
