package com.softuni.mobilelesec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.ModelEntity;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

}