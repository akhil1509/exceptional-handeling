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

	@Override
	public Student updatestd(Long id, Student student) {
		Student student2 = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("\"student not found at id: \"+id"));
		student2.setName(student.getName());
		student2.setEmail(student.getEmail());
		Student student3 = studentRepository.save(student2);
		return student3;
	}

	@Override
	public Student updatestdpartially(Long id, Student student) {
		// TODO Auto-generated method stub
		Student student2 = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("\"student not found at id: \"+id"));
         if(student.getName()!=null) {
        	 student2.setName(student.getName());
         }if(student.getEmail()!=null) {
        	 student2.setEmail(student.getEmail());
         }
         Student student3 = studentRepository.save(student2);
 		return student3;
	}

	@Override
	public String deleteStd(Long id) {
		studentRepository.deleteById(id);

		return "record is deleted successfully!";
	}


}
