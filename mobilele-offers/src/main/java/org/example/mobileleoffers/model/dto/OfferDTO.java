package org.example.mobileleoffers.model.dto;

import org.example.mobileleoffers.model.enums.EngineTypeEnum;
import org.example.mobileleoffers.model.enums.Transmission;

public record OfferDTO(
        Long id,
        String description,
        Integer mileage,
        Integer price,
        EngineTypeEnum engineType,
        Transmission transmission
) {

}
