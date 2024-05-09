package com.jarun.school.controller;
import com.jarun.school.model.Student;
import com.jarun.school.repository.StudentRepository;
import com.jarun.school.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    // GET all students
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        logger.info("Get All Students");
        return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    // POST create new student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        logger.info("Create Student : " , student.toString());
        return new ResponseEntity<Student>(studentService.saveStudent(student),HttpStatus.CREATED);
    }

    // GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        logger.info("Get Student By Id : ", id);
        Student student = studentService.getStudentById(id);
        return (student != null) ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    // PUT update existing student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        logger.info("Update Student :  ID - " + id + " Updating Student Object : ", updatedStudent.toString());
        Student student = studentService.updateStudent(id,updatedStudent);
        return (student !=null) ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    // DELETE student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        logger.info("Deleting Student By Id ", id);
        Student student = studentService.getStudentById(id);
        if (student != null) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student DELETED Successfully !");
        } else {
            logger.error(HttpStatus.NOT_FOUND.toString()," No Student Found for Given Student ID !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No Student Found for Given Student ID !");
        }
    }

    @GetMapping("/search/{filterValue}")
    public ResponseEntity<?> search(@PathVariable String filterValue){
        logger.info("Student Search BY : ", filterValue);
        return new ResponseEntity<List<Student>>(studentService.searchStudentsBYIdOrNameOrClassName(filterValue), HttpStatus.OK);
    }
}