package com.softuni.pathfindersec.web.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.softuni.pathfindersec.domain.entities.Comment;
import com.softuni.pathfindersec.domain.entities.UserEntity;
import com.softuni.pathfindersec.services.CommentService;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommentService commentService;

	@Test
	public void testGetAllComments_requestIsMade_allCommentsReturned() throws Exception {
		when(commentService.getCommentsByRoute(1l))
			.thenReturn(List.of(createComment("Test comment 1"), createComment("Test comment 2")));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1/comments"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].text", is("Test comment 1")))
				.andExpect(jsonPath("$.[0].user", is("Test User")))
				.andExpect(jsonPath("$.[1].text", is("Test comment 2")))
				.andExpect(jsonPath("$.[1].user", is("Test User")));
	}

	private Comment createComment(String text) {
		UserEntity author = new UserEntity();
		author.setUsername("testUser").setFullName("Test User");

		Comment comment = new Comment();
		comment.setCreated(LocalDateTime.now()).setText(text).setUser(author);

		return comment;
	}
}
