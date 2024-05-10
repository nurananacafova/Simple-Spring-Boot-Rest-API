package com.company.springdatajpa.repository;

import com.company.springdatajpa.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    //
    @Test
    public void StudentRepository_SaveAll_ReturnSavedStudent() {
        // Arrange
        Student student = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();

        //Act
        Student savedStudent = studentRepository.save(student);

        // Arrange
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void StudentRepository_GetAll_ReturnMoreThanOneStudent() {
        Student student1 = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();
        Student student2 = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("11")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> studentList = studentRepository.findAll();

        assertThat(studentList).isNotNull();
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void StudentRepository_FindById_ReturnStudent() {
        Student student = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();

        studentRepository.save(student);

        Student studentList = studentRepository.findById(student.getId()).get();

        assertThat(studentList).isNotNull();
    }

    @Test
    public void StudentRepository_FindByStudentNumber_ReturnNotNull() {
        Student student = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();

        studentRepository.save(student);

        Student studentList = studentRepository.findByStudentNumber(student.getStudentNumber()).get();

        assertThat(studentList).isNotNull();
    }

    @Test
    public void StudentRepository_UpdateStudent_ReturnNotNull() {
        Student student = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();

        studentRepository.save(student);
        Student savedStudent = studentRepository.findById(student.getId()).get();
        savedStudent.setName("A");
        savedStudent.setSurname("A");
        savedStudent.setStudentNumber("11");

        Student updatedStudent = studentRepository.save(savedStudent);

        assertThat(updatedStudent.getName()).isNotNull();
        assertThat(updatedStudent.getSurname()).isNotNull();
        assertThat(updatedStudent.getStudentNumber()).isNotNull();

    }


    @Test
    public void StudentRepository_StudentDelete_ReturnStudetnIsEmpty() {
        Student student = Student.builder()
                .name("N")
                .surname("N")
                .dateOfBirth(2001)
                .studentNumber("10")
                .build();

        studentRepository.save(student);

        studentRepository.deleteById(student.getId());
        Optional<Student> studentReturn = studentRepository.findById(student.getId());
        assertThat(studentReturn).isEmpty();
    }

    //from amigoscode
    @Test
    void itShouldCheckIfStudentEmailExists() {
        //Arrange
        Student student = new Student(
                1,
                "N",
                "N",
                12,
                "2");

        //Act
        studentRepository.save(student);
        //Assert
        boolean expected = studentRepository.selectExistName("N");
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        //Arrange
        Student student = new Student(
                1,
                "N",
                "N",
                12,
                "2");

        //Act
        boolean expected = studentRepository.selectExistName("N");
        //Assert
        assertThat(expected).isFalse();
    }
}