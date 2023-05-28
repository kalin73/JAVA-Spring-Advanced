package com.softuni.pathfinder.web.controllers.rest;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softuni.pathfinder.domain.dtos.model.CommentDto;
import com.softuni.pathfinder.domain.dtos.view.CommentView;
import com.softuni.pathfinder.domain.entities.Comment;
import com.softuni.pathfinder.domain.entities.UserEntity;
import com.softuni.pathfinder.domain.enums.RoleName;
import com.softuni.pathfinder.services.CommentService;
import com.softuni.pathfinder.services.UserService;

@RestController
public class CommentRestController {
	private final CommentService commentService;
	private final UserService userService;
	private final ModelMapper modelMapper;

	public CommentRestController(CommentService commentService, UserService userService, ModelMapper modelMapper) {
		this.commentService = commentService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/api/{routeId}/comments")
	public ResponseEntity<List<CommentView>> getCommentsRoutes(@PathVariable("routeId") Long routeId) {
		List<CommentView> comments = commentService.getCommentsByRoute(routeId).stream()
				.map(CommentView::mapToCommentView).toList();

		return ResponseEntity.ok(comments);
	}

	@GetMapping("/api/{routeId}/comments/{commentId}")
	public ResponseEntity<CommentView> getComment(@PathVariable("commentId") Long commentId) {
		return ResponseEntity.ok(CommentView.mapToCommentView(commentService.getComment(commentId)));

	}

	@PostMapping(value = "/api/{routeId}/comments", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommentView> createComment(@AuthenticationPrincipal UserDetails userDetails,
			@RequestBody CommentDto commentDto, @PathVariable Long routeId) {
		UserEntity author = modelMapper.map(userService.findByUsername(userDetails.getUsername()), UserEntity.class);

		Comment comment = commentService.createComment(commentDto, routeId, author);
		CommentView commentView = CommentView.mapToCommentView(comment);

		return ResponseEntity.created(URI.create(String.format("/api/%s/comments/%d", routeId, comment.getId())))
				.body(commentView);
	}

	@DeleteMapping("/api/{routeId}/comments/{commentId}")
	public ResponseEntity<CommentView> deleteComment(@PathVariable("commentId") Long commentId,
			@AuthenticationPrincipal UserDetails principal) {
		UserEntity user = modelMapper.map(userService.findByUsername(principal.getUsername()), UserEntity.class);
		Comment comment = commentService.getComment(commentId);

		if (user.getRoles().stream().anyMatch(r -> r.getRole() == RoleName.MODERATOR || r.getRole() == RoleName.ADMIN)
				|| user.getId() == comment.getUser().getId()) {
			Comment deleted = commentService.deleteComment(commentId);

			return ResponseEntity.ok(CommentView.mapToCommentView(deleted));
		}

		return ResponseEntity.status(403).build();

	}
}
