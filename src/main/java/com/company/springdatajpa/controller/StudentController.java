package com.company.springdatajpa.controller;

import com.company.springdatajpa.dto.StudentDto;
import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    public final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/new")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Integer id, @RequestBody StudentDto student) {
        StudentDto studentResponse = studentService.updateStudent(id, student);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
