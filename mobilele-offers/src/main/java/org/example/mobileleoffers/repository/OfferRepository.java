package org.example.mobileleoffers.repository;

import org.example.mobileleoffers.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
