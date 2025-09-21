package com.smartballot.controller;

import com.smartballot.model.Student;
import com.smartballot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String studentId,
                        @RequestParam String password,
                        Model model) {

        Student student = studentRepository.findByStudentIdAndPassword(studentId, password);

        if (student != null) {
            if ("admin".equalsIgnoreCase(student.getRole())) {
                return "admin-dashboard";
            } else {
                return "student-dashboard";
            }
        }

        model.addAttribute("error", "Invalid College ID or Password!");
        return "login";
    }
}
