package com.smartballot.repository;

import com.smartballot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentIdAndPassword(String studentId, String password);
}
