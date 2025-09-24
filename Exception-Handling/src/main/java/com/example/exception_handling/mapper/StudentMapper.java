package com.example.exception_handling.mapper;

import com.example.exception_handling.model.Student;
import com.example.exception_handling.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    StudentDto toDto(Student student);
    Student toEntity(StudentDto dto);
}