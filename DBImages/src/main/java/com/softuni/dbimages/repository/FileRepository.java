package com.softuni.dbimages.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.dbimages.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
