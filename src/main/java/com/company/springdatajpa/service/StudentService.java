package com.company.springdatajpa.service;

import com.company.springdatajpa.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudent();

    Optional<Student> getStudentById(int id);

    public Student createStudent(Student student);

    public void deleteStudent(Integer id);

    public Student updateStudent(Integer id, Student student);
}
