package com.jarun.school.controller;
import com.jarun.school.model.Student;
import com.jarun.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;

    // GET all students
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
    }

    // POST create new student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentRepository.save(student),HttpStatus.CREATED);
    }

    // GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT update existing student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            updatedStudent.setId(id); // Ensure the ID is set to the correct value
            if (updatedStudent.getName() != null) {
                student.setName(updatedStudent.getName());
            }
            if (updatedStudent.getDateOfBirth() != null) {
                student.setDateOfBirth(updatedStudent.getDateOfBirth());
            }
            if (updatedStudent.getJoiningDate() != null) {
                student.setJoiningDate(updatedStudent.getJoiningDate());
            }
            if (updatedStudent.getClassName() != null) {
                student.setClassName(updatedStudent.getClassName());
            }

            try {
                // Attempt to update the student
                studentRepository.save(student);
                return ResponseEntity.ok(student);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating student: " + e.getMessage());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student DELETED Successfully !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student Found for Given Student ID !");
        }
    }

    @GetMapping("/search/{filterValue}")
    public ResponseEntity<?> search(@PathVariable String filterValue){
        try {
            long id = Long.parseLong(filterValue);
            return new ResponseEntity<Optional<Student>>(studentRepository.findById(id), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<List<Student>>(studentRepository.findByFilterValue(filterValue),HttpStatus.OK);
        }
    }
}