package com.softuni.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.books.model.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
