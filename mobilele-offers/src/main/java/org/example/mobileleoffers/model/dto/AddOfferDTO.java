package org.example.mobileleoffers.model.dto;

import org.example.mobileleoffers.model.enums.EngineTypeEnum;

public record AddOfferDTO(
    String description,
    Integer mileage,
    Integer price,
    EngineTypeEnum engineType
) {
}
