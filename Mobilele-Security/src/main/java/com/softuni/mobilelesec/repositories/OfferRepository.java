package com.softuni.mobilelesec.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.OfferEntity;

public interface OfferRepository extends JpaRepository<OfferEntity, String> {
	Optional<OfferEntity> findOfferEntityByOfferId(UUID uuid);
}
