package com.example.exception_handling.service;

import java.util.List;

import com.example.exception_handling.model.Student;

public interface StudentService {
	//add Student
	public Student addStudent(Student student);
	
	// get Student by id
	
	public Student getStudentById(Long id);
	
	// get All Student
	
	public List<Student> getAllStudent();

}
