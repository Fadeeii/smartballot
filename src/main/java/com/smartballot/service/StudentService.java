package com.smartballot.service;

import com.smartballot.model.Student;
import com.smartballot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to add a student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
