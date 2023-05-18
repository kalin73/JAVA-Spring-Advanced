package com.softuni.caching.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softuni.caching.model.StudentDTO;

@Repository
public class StudentRepository {
	private Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);

	public List<StudentDTO> findAllStudents() {
		LOGGER.info("Downloading students...");
		dummyWait();
		LOGGER.info("Students downloaded...");

		return List.of(new StudentDTO().setAge(21).setName("Pesho"), new StudentDTO().setAge(22).setName("Anna"));
	}

	private void dummyWait() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
