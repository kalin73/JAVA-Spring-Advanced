package com.softuni.mobilelesec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.UserActivationLinkEntity;

public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity, Long> {

}
