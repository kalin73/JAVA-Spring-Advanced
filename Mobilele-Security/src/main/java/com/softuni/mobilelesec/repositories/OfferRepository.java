package com.softuni.mobilelesec.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.softuni.mobilelesec.domain.entities.OfferEntity;

public interface OfferRepository extends JpaRepository<OfferEntity, Long>, JpaSpecificationExecutor<OfferEntity> {
	Optional<OfferEntity> findOfferEntityByOfferId(UUID uuid);
}
