package com.softuni.caching.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softuni.caching.model.StudentDTO;
import com.softuni.caching.service.StudentServiceInterface;

@RestController
public class StudentController {
	private final StudentServiceInterface studentServiceInterface;

	public StudentController(StudentServiceInterface studentServiceInterface) {
		this.studentServiceInterface = studentServiceInterface;
	}

	@GetMapping("students/all")
	public ResponseEntity<List<StudentDTO>> findAll() {
		List<StudentDTO> studentsDTO = studentServiceInterface.getAllStudents();

		studentsDTO.forEach(System.out::println);

		return ResponseEntity.ok(studentsDTO);
	}

	@GetMapping("students/all/evict")
	public ResponseEntity<List<StudentDTO>> findAllAndEvict() {
		List<StudentDTO> studentsDTO = studentServiceInterface.getAllStudents();
		studentServiceInterface.refreshStudents();

		studentsDTO.forEach(System.out::println);

		return ResponseEntity.ok(studentsDTO);
	}

	@GetMapping("students/find")
	public ResponseEntity<StudentDTO> findStudentByName(@RequestParam("q") String q) {
		Optional<StudentDTO> studentDTO = studentServiceInterface.getStudentByName(q);

		return studentDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}
}
