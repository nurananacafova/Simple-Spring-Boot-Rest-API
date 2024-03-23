package com.company.springdatajpa.service;

import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
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
