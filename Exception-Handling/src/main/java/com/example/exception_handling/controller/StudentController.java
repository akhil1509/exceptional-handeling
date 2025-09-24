package com.example.exception_handling.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.exception_handling.dto.StudentDto;
import com.example.exception_handling.model.Student;
import com.example.exception_handling.service.StudentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private StudentServiceImpl studentServiceImpl;

	public StudentController(StudentServiceImpl studentServiceImpl) {
		
		this.studentServiceImpl = studentServiceImpl;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent( @Valid @RequestBody Student student){
		 Student addStd = studentServiceImpl.addStudent(student);
		 return new ResponseEntity<Student>(addStd, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		 Student getStdId = studentServiceImpl.getStudentById(id);
		 return new ResponseEntity<Student>(getStdId, HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> allStd = studentServiceImpl.getAllStudent();
		return new ResponseEntity<List<Student>>(allStd, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student std){
		Student updatestd = studentServiceImpl.updatestd(id, std);
		return new ResponseEntity<Student>(updatestd, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@PatchMapping("/updateStudentpartial/{id}")
	public ResponseEntity<Student> updateStudentpartial(@PathVariable Long id, @RequestBody Student std){
		Student updatestd = studentServiceImpl.updatestdpartially(id, std);
		return new ResponseEntity<Student>(updatestd, HttpStatus.OK);
		
	}


}
