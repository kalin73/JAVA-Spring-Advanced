package com.softuni.mobilelesec.domain.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import com.softuni.mobilelesec.domain.enums.Engine;
import com.softuni.mobilelesec.domain.enums.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//TODO: fix type in entity

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    @Column
    private String description;

    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID offerId;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column
    private String imageUrl;

    @Column
    private String mileage;

    @Column
    private String price;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column
    private String year;

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

    public Engine getEngine() {
        return engine;
    }

    public OfferEntity setEngine(Engine engine) {
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

    public String getMileage() {
        return mileage;
    }

    public OfferEntity setMileage(String mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public OfferEntity setPrice(String price) {
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

    public String getYear() {
        return year;
    }

    public OfferEntity setYear(String year) {
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
