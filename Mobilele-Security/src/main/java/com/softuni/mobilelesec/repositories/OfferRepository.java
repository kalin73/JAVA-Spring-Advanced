package com.softuni.mobilelesec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.OfferEntity;

public interface OfferRepository extends JpaRepository<OfferEntity, String> {

}
