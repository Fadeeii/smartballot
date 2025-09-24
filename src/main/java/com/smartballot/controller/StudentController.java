package com.smartballot.controller;

import com.smartballot.model.Election;
import com.smartballot.model.Student;
import com.smartballot.model.Vote;
import com.smartballot.repository.ElectionRepository;
import com.smartballot.repository.VoteRepository;
import com.smartballot.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteService voteService;

    // Helper method to get cast votes for the student
    @ModelAttribute("castedVotes")
    public Map<Long, List<Long>> castedVotes(HttpSession session) {
        Student student = (Student) session.getAttribute("loggedInUser");
        if (student == null) return Map.of();

        List<Vote> votes = voteRepository.findByStudentId(student.getId());
        return votes.stream()
                .collect(Collectors.groupingBy(v -> v.getElection().getId(),
                        Collectors.mapping(v -> v.getCandidate().getId(), Collectors.toList())));
    }

    // Show voting page
    @GetMapping("/vote")
    public String showVotingPage(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loggedInUser");
        if (student == null || !"student".equalsIgnoreCase(student.getRole())) {
            return "redirect:/login";
        }

        List<Election> activeElections = electionRepository.findByStatus("ACTIVE");
        model.addAttribute("elections", activeElections);
        model.addAttribute("student", student);
        System.out.println("Active elections: " + activeElections.size());


        return "student/vote";
    }

    // Cast vote
    @PostMapping("/vote")
    public String castVote(@RequestParam("electionid") Long electionId,
                           @RequestParam("candidateid") Long candidateId,
                           Model model,
                           HttpSession session) {

        Student student = (Student) session.getAttribute("loggedInUser");
        if (student == null || !"student".equalsIgnoreCase(student.getRole())) {
            return "redirect:/login";
        }

        String message = voteService.castVote(student.getId(), electionId, candidateId);
        model.addAttribute("message", message);

        List<Election> activeElections = electionRepository.findByStatus("ACTIVE");
        model.addAttribute("elections", activeElections);
        model.addAttribute("student", student);

        return "student/vote";
    }
}
