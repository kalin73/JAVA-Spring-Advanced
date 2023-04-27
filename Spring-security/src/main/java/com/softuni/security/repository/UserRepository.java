package com.softuni.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.security.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
