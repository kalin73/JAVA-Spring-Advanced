package com.softuni.mobilelesec.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.softuni.mobilelesec.domain.dtos.binding.OfferCreationDto;
import com.softuni.mobilelesec.domain.dtos.model.SearchOfferDTO;
import com.softuni.mobilelesec.domain.dtos.view.OfferDetailsViewDTO;
import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.OfferRepository;
import com.softuni.mobilelesec.repositories.OfferSpecification;
import com.softuni.mobilelesec.repositories.UserRepository;
import com.softuni.mobilelesec.services.exception.ObjectNotFoundException;
import org.springframework.web.client.RestClient;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final RestClient restClient;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, @Qualifier("offersRestClient") RestClient restClient) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.restClient = restClient;
    }

    public Page<OfferDetailsViewDTO> getAllOffers(Pageable pageable) {
        return this.offerRepository.findAll(pageable).map(this::map);
    }

    public void addOffer(OfferCreationDto offerCreationDto, String email) {
        UserEntity seller = this.userRepository.findByEmail(email).orElseThrow();

        restClient.put()
                .uri("/api/offers")
                .body(offerCreationDto)
                .retrieve();
    }

    private OfferDetailsViewDTO map(OfferEntity offerEntity) {
        return new OfferDetailsViewDTO().setOfferId(offerEntity.getOfferId()).setImageUrl(offerEntity.getImageUrl())
                .setDescription(offerEntity.getDescription()).setEngine(offerEntity.getEngine())
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

    public List<OfferDetailsViewDTO> findOffers(SearchOfferDTO filter) {
        return this.offerRepository.findAll(new OfferSpecification(filter)).stream().map(this::map).toList();
    }

    private boolean isUserAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().stream().map(a -> a.getAuthority())
                .anyMatch(a -> a.equals("ROLE_" + UserRoleEnum.ADMIN.name()));
    }
}
