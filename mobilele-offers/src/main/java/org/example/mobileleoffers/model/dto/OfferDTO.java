package org.example.mobileleoffers.model.dto;

import org.example.mobileleoffers.model.enums.Engine;
import org.example.mobileleoffers.model.enums.Transmission;

public record OfferDTO(
        Long id, Engine engine, String description, String imageUrl, String mileage, String price,
        Transmission transmission, String year
) {

}
