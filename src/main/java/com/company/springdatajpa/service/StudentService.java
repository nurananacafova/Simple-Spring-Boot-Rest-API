package com.company.springdatajpa.service;

import com.company.springdatajpa.dto.StudentDto;
import com.company.springdatajpa.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudent();

    StudentDto getStudentById(int id);

    StudentDto createStudent(StudentDto studentDto);

    void deleteStudent(Integer id);

    StudentDto updateStudent(Integer id, StudentDto studentDto);
}
