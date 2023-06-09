package com.softuni.books.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.softuni.books.model.dto.BookDTO;
import com.softuni.books.repository.BookRepository;
import com.softuni.books.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/books")
public class BooksController {
	private final BookService bookService;
	private final BookRepository bookRepository;

	public BooksController(BookService bookService, BookRepository bookRepository) {
		this.bookService = bookService;
		this.bookRepository = bookRepository;
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@Operation(summary = "Get book by the given ID")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "If the book was discoverd", content = {
					@Content(mediaType = "applivation/json", schema = @Schema(implementation = BookDTO.class)) }), 
			@ApiResponse(responseCode = "400", description = "If the ID was incorect."),
			@ApiResponse(responseCode = "404", description = "If the book was not found.")
		}
	)
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable(name = "id") Long id) {
		Optional<BookDTO> theBook = bookService.getBookById(id);

		return theBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void deleteBookById(@PathVariable Long id) {
		this.bookRepository.findById(id).ifPresent(bookRepository::delete);
	}

	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book, UriComponentsBuilder uriComponentsBuilder) {
		Long id = bookService.createBook(book);

		return ResponseEntity.created(uriComponentsBuilder.path("/api/book/{id}").build(id)).build();

	}
}
