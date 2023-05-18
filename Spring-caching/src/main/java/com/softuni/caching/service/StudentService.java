package com.softuni.caching.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.softuni.caching.model.StudentDTO;
import com.softuni.caching.repository.StudentRepository;

@Service
public class StudentService implements StudentServiceInterface {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	@Cacheable("students")
	public List<StudentDTO> getAllStudents() {
		return studentRepository.findAllStudents();
	}

	@Override
	@Cacheable("student")
	public Optional<StudentDTO> getStudentByName(String name) {
		return this.studentRepository.findAllStudents().stream().filter(st -> st.getName().equals(name)).findAny();
	}

	@Override
	@CacheEvict(cacheNames = "students", allEntries = true)
	public void refreshStudents() {

	}

}
