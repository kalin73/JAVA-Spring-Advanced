package com.softuni.mobilelesec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

}
