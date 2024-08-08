package com.softuni.mobilelesec.services;

import com.softuni.mobilelesec.domain.dtos.binding.OfferCreationDto;
import com.softuni.mobilelesec.domain.dtos.model.PageResponse;
import com.softuni.mobilelesec.domain.dtos.model.SearchOfferDTO;
import com.softuni.mobilelesec.domain.dtos.view.OfferDetailsViewDTO;
import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.OfferRepository;
import com.softuni.mobilelesec.repositories.OfferSpecification;
import com.softuni.mobilelesec.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final RestClient restClient;

    public OfferService(OfferRepository offerRepository, @Qualifier("offersRestClient") RestClient restClient) {
        this.offerRepository = offerRepository;
        this.restClient = restClient;
    }

    public Page<OfferDetailsViewDTO> getAllOffers(Pageable pageable) {
        PageResponse<OfferDetailsViewDTO> offers = this.restClient.get()
                .uri("/api/offers?page={page}&size={size}&sort=id,desc",
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        assert offers != null;

        return new PageImpl<>(offers.getContent(), pageable, offers.getPage().totalElements());

//        return this.offerRepository.findAll(pageable).map(this::map);
    }

    public void addOffer(OfferCreationDto offerCreationDto) {
        restClient.post()
                .uri("/api/offers")
                .body(offerCreationDto)
                .retrieve();
    }

    private OfferDetailsViewDTO map(OfferEntity offerEntity) {
        return new OfferDetailsViewDTO()
                .setOfferId(offerEntity.getOfferId())
                .setImageUrl(offerEntity.getImageUrl())
                .setDescription(offerEntity.getDescription())
                .setEngine(offerEntity.getEngine())
                .setMileage(offerEntity.getMileage().toString())
                .setPrice(offerEntity.getPrice().toString())
                .setTransmission(offerEntity.getTransmission()).setYear(offerEntity.getYear().toString())
                .setSeller(offerEntity.getSeller().getFirstName() + " " + offerEntity.getSeller().getLastName());
    }

    public void deleteOfferByUUID(UUID id) {
        this.restClient
                .delete()
                .uri("/api/offers/{id}", id)
                .retrieve();
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
        return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_" + UserRoleEnum.ADMIN.name()));
    }
}
