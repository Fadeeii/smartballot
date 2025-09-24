package com.smartballot.controller;

import com.smartballot.model.Student;
import com.smartballot.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
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
    public String login(@RequestParam String studentId,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        Student student = studentRepository.findByStudentIdAndPassword(studentId, password);

        if (student != null && student.getRole() != null) {
            // Store logged-in user in session
            session.setAttribute("loggedInUser", student);

            if ("admin".equalsIgnoreCase(student.getRole().trim())) {
                return "admin/dashboard";
            } else if ("student".equalsIgnoreCase(student.getRole().trim())) {
                return "student/dashboard";
            } else {
                model.addAttribute("error", "User role not recognized!");
                return "login";
            }
        }

        model.addAttribute("error", "Invalid College ID or Password!");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
