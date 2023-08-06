package com.company.springdatajpa.service;

import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Integer id, Student student) {
        Optional<Student> foundedStudent = studentRepository.findById(id);
        if (foundedStudent.isPresent()) {
            Student newStudent = foundedStudent.get();
            newStudent.setName(student.getName());
            newStudent.setSurname(student.getSurname());
            newStudent.setDateOfBirth(student.getDateOfBirth());
            newStudent.setStudentNumber(student.getStudentNumber());
            return studentRepository.save(newStudent);
        }
        return null;
    }
}
