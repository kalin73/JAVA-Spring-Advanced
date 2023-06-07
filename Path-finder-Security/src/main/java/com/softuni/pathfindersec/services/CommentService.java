package com.softuni.pathfindersec.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.pathfindersec.domain.dtos.model.CommentDto;
import com.softuni.pathfindersec.domain.entities.Comment;
import com.softuni.pathfindersec.domain.entities.Route;
import com.softuni.pathfindersec.domain.entities.UserEntity;
import com.softuni.pathfindersec.repositories.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final RouteService routeService;
	private final ModelMapper modelMapper;

	public CommentService(CommentRepository commentRepository, RouteService routeService, ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.routeService = routeService;
		this.modelMapper = modelMapper;
	}

	public List<Comment> getCommentsByRoute(Long routeId) {
		Route route = modelMapper.map(routeService.findById(routeId), Route.class);
		List<Comment> comments = commentRepository.findAllByRoute(route);

		return comments;
	}

	public Comment createComment(CommentDto commentDto, Long routId, UserEntity author) {
		Comment comment = new Comment();
		comment.setCreated(LocalDateTime.now()).setRoute(modelMapper.map(routeService.findById(routId), Route.class))
				.setUser(author).setText(commentDto.getText()).setApproved(true);

		return commentRepository.save(comment);
	}

	public Comment getComment(Long id) {
		return this.commentRepository.findById(id).orElseThrow();
	}

	public Comment deleteComment(Long id) {
		Comment comment = getComment(id);
		this.commentRepository.delete(comment);

		return comment;
	}

}
