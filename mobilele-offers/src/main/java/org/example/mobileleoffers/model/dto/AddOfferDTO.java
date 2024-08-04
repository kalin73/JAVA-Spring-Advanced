package org.example.mobileleoffers.model.dto;

import org.example.mobileleoffers.model.enums.Engine;
import org.example.mobileleoffers.model.enums.Transmission;

import java.math.BigDecimal;

public record AddOfferDTO(String model, BigDecimal price, Engine engine, Transmission transmission,
                          Integer year,
                          Integer mileage, String description, String imageUrl
) {
}
