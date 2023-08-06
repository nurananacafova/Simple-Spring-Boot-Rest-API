package com.company.springdatajpa.controller;

import com.company.springdatajpa.entity.Student;
import com.company.springdatajpa.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    public final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @PostMapping("/new")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id){
         studentService.deleteStudent(id);
    }
    @PutMapping("/edit/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }
}
