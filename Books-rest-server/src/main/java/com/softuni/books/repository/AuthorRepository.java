package com.softuni.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.books.model.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
	Optional<AuthorEntity> findAuthorEntityByName(String name);
}
