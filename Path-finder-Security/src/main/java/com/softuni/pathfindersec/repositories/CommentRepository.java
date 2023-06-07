package com.softuni.pathfindersec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfindersec.domain.entities.Comment;
import com.softuni.pathfindersec.domain.entities.Route;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByRoute(Route route);
}
