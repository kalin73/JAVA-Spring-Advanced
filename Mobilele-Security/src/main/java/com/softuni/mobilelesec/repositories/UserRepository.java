package com.softuni.mobilelesec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilelesec.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	Optional<UserEntity> findByEmail(String username);
}
