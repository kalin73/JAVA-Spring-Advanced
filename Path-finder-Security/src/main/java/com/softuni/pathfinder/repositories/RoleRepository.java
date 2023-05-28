package com.softuni.pathfinder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfinder.domain.entities.Role;
import com.softuni.pathfinder.domain.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRole(RoleName role);
}
