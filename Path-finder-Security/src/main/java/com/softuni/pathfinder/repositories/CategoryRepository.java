package com.softuni.pathfinder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfinder.domain.entities.Category;
import com.softuni.pathfinder.domain.enums.CategoryName;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(CategoryName name);
}
