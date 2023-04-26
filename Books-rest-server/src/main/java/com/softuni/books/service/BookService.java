package com.softuni.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softuni.books.model.dto.AuthorDTO;
import com.softuni.books.model.dto.BookDTO;
import com.softuni.books.model.entity.AuthorEntity;
import com.softuni.books.model.entity.BookEntity;
import com.softuni.books.repository.AuthorRepository;
import com.softuni.books.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	public List<BookDTO> getAllBooks() {
		return this.bookRepository.findAll().stream().map(this::map).toList();
	}

	private BookDTO map(BookEntity bookEntity) {
		AuthorDTO authorDto = new AuthorDTO();
		authorDto.setName(bookEntity.getAuthor().getName());

		BookDTO bookDto = new BookDTO();
		bookDto.setId(bookEntity.getId());
		bookDto.setTitle(bookEntity.getTitle());
		bookDto.setIsbn(bookEntity.getIsbn());
		bookDto.setAuthor(authorDto);

		return bookDto;
	}

	public Optional<BookDTO> getBookById(Long id) {
		return bookRepository.findById(id).map(this::map);
	}

	public void deleteById(Long id) {
		this.bookRepository.deleteById(id);
	}

	public Long createBook(BookDTO bookDTO) {
		String authorName = bookDTO.getAuthor().getName();
		Optional<AuthorEntity> authorOpt = this.authorRepository.findAuthorEntityByName(authorName);

		BookEntity newBookEntity = new BookEntity().setTitle(bookDTO.getTitle()).setIsbn(bookDTO.getIsbn())
				.setAuthor(authorOpt.orElseGet(() -> createNewAuthor(authorName)));

		return this.bookRepository.save(newBookEntity).getId();

	}

	private AuthorEntity createNewAuthor(String authorName) {
		return this.authorRepository.save(new AuthorEntity().setName(authorName));
	}
}
