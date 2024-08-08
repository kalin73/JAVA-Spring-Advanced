package org.example.mobileleoffers.repository;

import org.example.mobileleoffers.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    @Modifying
    @Transactional
    void deleteOfferEntityByOfferId(UUID id);
}
