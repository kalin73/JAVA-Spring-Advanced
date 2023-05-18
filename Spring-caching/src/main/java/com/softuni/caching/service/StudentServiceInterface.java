package com.softuni.caching.service;

import java.util.List;
import java.util.Optional;

import com.softuni.caching.model.StudentDTO;

public interface StudentServiceInterface {
	List<StudentDTO> getAllStudents();
	
	Optional<StudentDTO> getStudentByName(String name);
	
	void refreshStudents();
	
}
