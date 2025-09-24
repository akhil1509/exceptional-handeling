package com.example.exception_handling.controller;

import com.example.exception_handling.model.Student;
import com.example.exception_handling.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentServiceImpl;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setEmail("john@mail.com");
    }

    @Test
   // @WithMockUser(roles = "ADMIN")
    void testAddStudent() throws Exception {
        when(studentServiceImpl.addStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/student/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
   // @WithMockUser(roles = {"ADMIN", "USER"})
    void testGetStudentById() throws Exception {
        when(studentServiceImpl.getStudentById(1L)).thenReturn(student);
        mockMvc.perform(get("/student/getStudentById/1"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
   // @WithMockUser(roles = {"ADMIN", "USER"})
    void testGetAllStudent() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentServiceImpl.getAllStudent()).thenReturn(students);
        mockMvc.perform(get("/student/getAllStudent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
   // @WithMockUser(roles = "ADMIN")
    void testUpdateStudent() throws Exception {
        when(studentServiceImpl.updatestd(eq(1L), any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/student/updateStudent/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
   // @WithMockUser(roles = {"ADMIN", "MANAGER"})
    void testUpdateStudentPartial() throws Exception {
        when(studentServiceImpl.updatestdpartially(eq(1L), any(Student.class))).thenReturn(student);
        mockMvc.perform(patch("/student/updateStudentpartial/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
