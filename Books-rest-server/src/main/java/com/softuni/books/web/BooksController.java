package com.softuni.books.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.softuni.books.model.dto.BookDTO;
import com.softuni.books.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BooksController {
	private final BookService bookService;

	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable(name = "id") Long id) {
		Optional<BookDTO> theBook = bookService.getBookById(id);

		return theBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id) {
		this.bookService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book, UriComponentsBuilder uriComponentsBuilder) {
		Long id = bookService.createBook(book);

		return ResponseEntity
				.created(uriComponentsBuilder.path("/api/book/{id}").build(id))
				.build();

	}
}
