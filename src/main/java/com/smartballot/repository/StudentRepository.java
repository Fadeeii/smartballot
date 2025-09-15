package com.smartballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartballot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can add custom query methods here later if needed
}
