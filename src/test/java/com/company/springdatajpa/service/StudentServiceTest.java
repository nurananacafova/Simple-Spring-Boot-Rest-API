package com.company.springdatajpa.service;

import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;

    // Before each test we get new instance of the studentservice
    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void canGetAllStudent() {
        studentService.getAllStudent();
        verify(studentRepository).findAll();
    }

    @Test
    void canCreateStudent() {
        // Act
        Student student = new Student(
                1,
                "Nurana",
                "Najafova",
                12,
                "2");
        // Arrange
        studentService.createStudent(student);

        // Assert
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    @Disabled
    void deleteStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}