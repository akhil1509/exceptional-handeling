package com.example.exception_handling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exception_handling.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
