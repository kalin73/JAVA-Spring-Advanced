package com.softuni.mobilelesec.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softuni.mobilelesec.domain.dtos.view.OfferDetailsViewDTO;
import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.repositories.OfferRepository;

@Service
public class OfferService {
	private final OfferRepository offerRepository;

	public OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	public Page<OfferDetailsViewDTO> getAllOffers(Pageable pageable) {
		return this.offerRepository.findAll(pageable).map(this::map);
	}
	 private OfferDetailsViewDTO map(OfferEntity offerEntity) {
		    return new OfferDetailsViewDTO().
		        setOfferId(offerEntity.getOfferId()).
		        setImageUrl(offerEntity.getImageUrl()).
		        setDescription(offerEntity.getDescription()).
		        setEngine(offerEntity.getEngine()).
		        setModel(offerEntity.getModel().getName()).
		        setModel(offerEntity.getModel().getBrand().getName()).
		        setMileage(offerEntity.getMileage().toString()).// TODO -> int
		        setPrice(offerEntity.getPrice().toString()).// TODO -> big decimal
		        setTransmission(offerEntity.getTransmission()).
		        setYear(offerEntity.getYear().toString());//TODO -> int
		  }
}
