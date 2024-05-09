package com.jarun.school.service.impl;

import com.jarun.school.model.Student;
import com.jarun.school.repository.StudentRepository;
import com.jarun.school.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
       // return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Long id,Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if (existingStudent.isPresent()) {
            Student updateStudent = existingStudent.get();
            if (student.getName() != null) {
                updateStudent.setName(student.getName());
            }
            if (student.getDateOfBirth() != null) {
                updateStudent.setDateOfBirth(student.getDateOfBirth());
            }
            if (student.getJoiningDate() != null) {
                updateStudent.setJoiningDate(student.getJoiningDate());
            }
            if (student.getClassName() != null) {
                updateStudent.setClassName(student.getClassName());
            }
            return studentRepository.save(updateStudent);
        }else{
            return null;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchStudentsBYIdOrNameOrClassName(String filterValue) {
        try {
            long id = Long.parseLong(filterValue);
            return studentRepository.findById(id).map(Collections::singletonList) // Map to a singleton list if present
                                                 .orElse(Collections.emptyList());
        } catch (NumberFormatException e) {
            return studentRepository.findByFilterValue(filterValue);
        }
    }
}
