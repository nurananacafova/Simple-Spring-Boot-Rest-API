package com.company.springdatajpa.service.impl;

import com.company.springdatajpa.dto.StudentDto;
import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.exception.StudentNotFoundException;
import com.company.springdatajpa.repository.StudentRepository;
import com.company.springdatajpa.service.StudentService;
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
    public StudentDto getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return mapToDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setStudentNumber(studentDto.getStudentNumber());

        Student newStudent = studentRepository.save(student);

        StudentDto studentResponse = new StudentDto();
        studentResponse.setId(newStudent.getId());
        studentResponse.setName(newStudent.getName());
        studentResponse.setSurname(newStudent.getSurname());
        studentResponse.setDateOfBirth(newStudent.getDateOfBirth());
        studentResponse.setStudentNumber(newStudent.getStudentNumber());
        return studentResponse;
    }

    @Override
    public void deleteStudent(Integer id) {
        Student foundedStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        studentRepository.deleteById(foundedStudent.getId());
    }

    @Override
    public StudentDto updateStudent(Integer id, StudentDto studentDto) {
        Student foundedStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        foundedStudent.setName(studentDto.getName());
        foundedStudent.setSurname(studentDto.getSurname());
        foundedStudent.setDateOfBirth(studentDto.getDateOfBirth());
        foundedStudent.setStudentNumber(studentDto.getStudentNumber());
        Student updatedStudent = studentRepository.save(foundedStudent);

        return mapToDto(updatedStudent);
    }


    private StudentDto mapToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setStudentNumber(student.getStudentNumber());
        return studentDto;
    }

    private Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
//        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setStudentNumber(studentDto.getStudentNumber());
        return student;
    }

}
