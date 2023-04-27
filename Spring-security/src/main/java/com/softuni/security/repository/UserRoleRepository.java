package com.softuni.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.security.model.entity.UserRoleEntity;
import com.softuni.security.model.enums.UserRoleEnum;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
	Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);
}
