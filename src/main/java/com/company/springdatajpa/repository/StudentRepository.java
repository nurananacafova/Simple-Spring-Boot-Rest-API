package com.company.springdatajpa.repository;

import com.company.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("" +
            "SELECT CASE WHEN COUNT (s)>0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Student s " +
            "WHERE s.name=?1")
    Boolean selectExistName(String name);

    Optional<Student> findByStudentNumber(String name);

}
