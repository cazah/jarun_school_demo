package com.jarun.school.repository;

import com.jarun.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom query method to find students by name
    Optional<Student> findByName(String name);
    // Custom query method to find students by class name
    Optional<Student> findByClassName(String className);

    //@Query("SELECT s FROM Student s WHERE s.name = :filterValue OR s.className = :filterValue")
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) = LOWER(:filterValue) OR LOWER(s.className) = LOWER(:filterValue)")
    List<Student> findByFilterValue(String filterValue);

}
