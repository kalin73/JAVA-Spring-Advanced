package com.softuni.pathfindersec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfindersec.domain.entities.Role;
import com.softuni.pathfindersec.domain.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRole(RoleName role);
}
