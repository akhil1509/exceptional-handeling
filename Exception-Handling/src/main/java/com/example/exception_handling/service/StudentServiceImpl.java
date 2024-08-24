package com.example.exception_handling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exception_handling.exception.ResourceNotFoundException;
import com.example.exception_handling.model.Student;
import com.example.exception_handling.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
	}

	@Override
	public Student addStudent(Student student) {
		
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		Student git = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("student not found at id: "+id));
		return git;
	}

	@Override
	public List<Student> getAllStudent() {
		
		return studentRepository.findAll();
	}
	

}
