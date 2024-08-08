package org.example.mobileleoffers.service;

import org.example.mobileleoffers.model.dto.AddOfferDTO;
import org.example.mobileleoffers.model.dto.OfferDTO;
import org.example.mobileleoffers.model.entity.OfferEntity;
import org.example.mobileleoffers.model.entity.UserEntity;
import org.example.mobileleoffers.repository.OfferRepository;
import org.example.mobileleoffers.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    public OfferDTO addOffer(AddOfferDTO addOfferDTO, String email) {
        UserEntity seller = this.userRepository.findByEmail(email).orElseThrow();

        return mapToDto(this.offerRepository.save(mapToEntity(addOfferDTO, seller)));
    }

    public PagedModel<OfferDTO> getAllOffers(Pageable pageable) {
        var offers = this.offerRepository.findAll(pageable).map(this::mapToDto);

        return new PagedModel<>(offers);

    }

    public OfferDTO getOfferById(Long id) {
        return this.offerRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Could not find offer with id: " + id));
    }

    public void deleteOfferById(UUID id) {
        this.offerRepository.deleteOfferEntityByOfferId(id);
    }

    private OfferDTO mapToDto(OfferEntity offerEntity) {
        return new OfferDTO(offerEntity.getOfferId(),
                offerEntity.getEngine(),
                offerEntity.getDescription(),
                offerEntity.getImageUrl(),
                offerEntity.getMileage().toString(),
                offerEntity.getPrice().toString(),
                offerEntity.getTransmission(),
                offerEntity.getYear().toString());
    }

    private OfferEntity mapToEntity(AddOfferDTO addOfferDTO, UserEntity userEntity) {
        return new OfferEntity()
                .setOfferId(UUID.randomUUID())
                .setEngine(addOfferDTO.engine())
                .setDescription(addOfferDTO.description())
                .setImageUrl(addOfferDTO.imageUrl())
                .setMileage(addOfferDTO.mileage())
                .setPrice(addOfferDTO.price())
                .setTransmission(addOfferDTO.transmission())
                .setYear(addOfferDTO.year())
                .setSeller(userEntity);
    }
}
