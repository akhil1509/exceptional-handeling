package com.example.exception_handling.service;

import com.example.exception_handling.exception.ResourceNotFoundException;
import com.example.exception_handling.model.Student;
import com.example.exception_handling.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);
        Student result = studentService.addStudent(student);
        assertEquals(student, result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById_Found() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById(1L);
        assertEquals(student, result);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void testGetAllStudent() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getAllStudent();
        assertEquals(students, result);
    }

    @Test
    void testUpdatestd_Found() {
        Student existing = new Student();
        existing.setId(1L);
        existing.setName("Old");
        existing.setEmail("old@mail.com");
        Student update = new Student();
        update.setName("New");
        update.setEmail("new@mail.com");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(existing)).thenReturn(existing);
        Student result = studentService.updatestd(1L, update);
        assertEquals("New", result.getName());
        assertEquals("new@mail.com", result.getEmail());
    }

    @Test
    void testUpdatestd_NotFound() {
        Student update = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.updatestd(1L, update));
    }

    @Test
    void testUpdatestdpartially_Found() {
        Student existing = new Student();
        existing.setId(1L);
        existing.setName("Old");
        existing.setEmail("old@mail.com");
        Student update = new Student();
        update.setName("Partial");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(existing)).thenReturn(existing);
        Student result = studentService.updatestdpartially(1L, update);
        assertEquals("Partial", result.getName());
        assertEquals("old@mail.com", result.getEmail());
    }

    @Test
    void testUpdatestdpartially_NotFound() {
        Student update = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.updatestdpartially(1L, update));
    }

    @Test
    void testDeleteStd() {
        doNothing().when(studentRepository).deleteById(1L);
        String result = studentService.deleteStd(1L);
        assertEquals("record is deleted successfully!", result);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
