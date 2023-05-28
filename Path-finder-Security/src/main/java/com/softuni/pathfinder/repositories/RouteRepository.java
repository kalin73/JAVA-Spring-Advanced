package com.softuni.pathfinder.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softuni.pathfinder.domain.entities.Category;
import com.softuni.pathfinder.domain.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

	@Query("SELECT r FROM Route r ORDER BY SIZE(r.comments) desc")
	Optional<List<Route>> findMostCommented();

	Optional<List<Route>> findAllByCategoriesContains(Category category);
}
