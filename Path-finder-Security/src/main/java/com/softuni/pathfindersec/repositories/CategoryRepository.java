package com.softuni.pathfindersec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfindersec.domain.entities.Category;
import com.softuni.pathfindersec.domain.enums.CategoryName;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(CategoryName name);
}
