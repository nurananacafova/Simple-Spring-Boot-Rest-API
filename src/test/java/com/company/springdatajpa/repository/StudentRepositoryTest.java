package com.company.springdatajpa.repository;

import com.company.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void itShouldCheckIfStudentEmailExists() {
        //Arrange

        Student student = new Student(
                1,
                "Nurana",
                "Najafova",
                12,
                "2");

        //Act

        studentRepository.save(student);
        //Assert
        boolean expected = studentRepository.selectExistName("Nurana");
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        //Arrange

        Student student = new Student(
                1,
                "Nurana",
                "Najafova",
                12,
                "2");

        //Act
        boolean expected = studentRepository.selectExistName("Nurana");

        //Assert
        assertThat(expected).isFalse();
    }
}