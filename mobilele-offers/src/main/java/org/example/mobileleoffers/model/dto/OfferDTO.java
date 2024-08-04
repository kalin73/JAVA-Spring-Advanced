package org.example.mobileleoffers.model.dto;

import org.example.mobileleoffers.model.enums.Engine;
import org.example.mobileleoffers.model.enums.Transmission;

import java.util.UUID;

public record OfferDTO(
        UUID offerId, Engine engine, String description, String imageUrl, String mileage, String price,
        Transmission transmission, String year
) {

}
