package com.example.exception_handling.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception_handling.model.Student;
import com.example.exception_handling.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private StudentServiceImpl studentServiceImpl;

	public StudentController(StudentServiceImpl studentServiceImpl) {
		
		this.studentServiceImpl = studentServiceImpl;
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		 Student addStd = studentServiceImpl.addStudent(student);
		 return new ResponseEntity<Student>(addStd, HttpStatus.CREATED);
	}
	
	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		 Student getStdId = studentServiceImpl.getStudentById(id);
		 return new ResponseEntity<Student>(getStdId, HttpStatus.FOUND);
	}
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> allStd = studentServiceImpl.getAllStudent();
		return new ResponseEntity<List<Student>>(allStd, HttpStatus.OK);
	}

}
