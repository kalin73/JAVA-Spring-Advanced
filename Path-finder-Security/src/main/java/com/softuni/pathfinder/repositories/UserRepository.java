package com.softuni.pathfinder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfinder.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
}
