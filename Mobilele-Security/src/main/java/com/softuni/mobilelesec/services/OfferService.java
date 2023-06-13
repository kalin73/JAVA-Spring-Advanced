package com.softuni.mobilelesec.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.softuni.mobilelesec.domain.dtos.view.OfferDetailsViewDTO;
import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.OfferRepository;
import com.softuni.mobilelesec.services.exception.ObjectNotFoundException;

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
		return new OfferDetailsViewDTO().setOfferId(offerEntity.getOfferId()).setImageUrl(offerEntity.getImageUrl())
				.setDescription(offerEntity.getDescription()).setEngine(offerEntity.getEngine())
				.setModel(offerEntity.getModel().getName()).setModel(offerEntity.getModel().getBrand().getName())
				.setMileage(offerEntity.getMileage().toString()).// TODO -> int
				setPrice(offerEntity.getPrice().toString()).// TODO -> big decimal
				setTransmission(offerEntity.getTransmission()).setYear(offerEntity.getYear().toString());// TODO -> int
	}

	public void deleteOfferByUUID(UUID id) {
		offerRepository.findOfferEntityByOfferId(id).ifPresent(offerRepository::delete);
	}

	public boolean isOwner(UserDetails userDetails, UUID id) {
		if (id == null || userDetails == null) {
			return false;
		}

		var offer = offerRepository.findOfferEntityByOfferId(id).orElse(null);

		if (offer == null) {
			return false;
		}

		return userDetails.getUsername().equals(offer.getSeller().getEmail()) || isUserAdmin(userDetails);
	}

	public OfferDetailsViewDTO getOfferById(UUID offerId) {
		var offerEntity = offerRepository.findOfferEntityByOfferId(offerId)
				.orElseThrow(() -> new ObjectNotFoundException("Offer " + offerId + " not found!", offerId));

		return map(offerEntity);
	}

	private boolean isUserAdmin(UserDetails userDetails) {
		return userDetails.getAuthorities().stream().map(a -> a.getAuthority())
				.anyMatch(a -> a.equals("ROLE_" + UserRoleEnum.ADMIN.name()));
	}
}
