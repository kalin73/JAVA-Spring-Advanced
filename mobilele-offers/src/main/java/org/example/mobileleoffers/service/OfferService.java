package org.example.mobileleoffers.service;

import org.example.mobileleoffers.model.dto.AddOfferDTO;
import org.example.mobileleoffers.model.dto.OfferDTO;
import org.example.mobileleoffers.model.entity.OfferEntity;
import org.example.mobileleoffers.repository.OfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {

    }

    public PagedModel<OfferDTO> getAllOffers(Pageable pageable) {
        var offers = this.offerRepository.findAll(pageable).map(this::map);

        return new PagedModel<>(offers);

    }

    private OfferDTO map(OfferEntity offerEntity) {
        return new OfferDTO(offerEntity.getOfferId(),
                offerEntity.getEngine(),
                offerEntity.getDescription(),
                offerEntity.getImageUrl(),
                offerEntity.getMileage().toString(),
                offerEntity.getPrice().toString(),
                offerEntity.getTransmission(),
                offerEntity.getYear().toString());
    }
}
