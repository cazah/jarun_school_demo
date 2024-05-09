package com.jarun.school.service;

import com.jarun.school.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Long id,Student student);
    boolean deleteStudent(Long id);
    List<Student> searchStudentsBYIdOrNameOrClassName(String fileterVal);
}
