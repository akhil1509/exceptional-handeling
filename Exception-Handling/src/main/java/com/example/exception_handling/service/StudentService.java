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
	
	//update the data
	public Student updatestd(Long id, Student student);
	
	//partially update
	public Student updatestdpartially(Long id, Student student);

	//delete the record

	public String deleteStd(Long id);

}
