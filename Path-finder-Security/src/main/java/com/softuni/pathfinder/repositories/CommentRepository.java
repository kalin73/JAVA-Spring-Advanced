package com.softuni.pathfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfinder.domain.entities.Comment;
import com.softuni.pathfinder.domain.entities.Route;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByRoute(Route route);
}
