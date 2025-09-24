package com.smartballot.controller;

import com.smartballot.model.Candidate;
import com.smartballot.model.Election;
import com.smartballot.repository.CandidateRepository;
import com.smartballot.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @GetMapping({"/dashboard", ""})
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/start-election")
    public String startElection(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "admin/start-election";
    }

    @PostMapping("/start-election")
    public String addCandidate(@RequestParam("candidate_name") String candidateName,
                               @RequestParam("party_name") String partyName,
                               @RequestParam("election_name") String electionName,
                               @RequestParam("election_date") String electionDate) {

        // Find existing election or create new
        Election election = electionRepository.findByName(electionName)
                .orElseGet(() -> {
                    Election e = new Election();
                    e.setName(electionName);
                    e.setElectionDate(java.time.LocalDate.parse(electionDate));
                    return electionRepository.save(e);
                });

        // Save the candidate
        Candidate candidate = new Candidate(candidateName, partyName, electionDate, "10:00"); // placeholder time
        candidate.setElection(election);
        candidateRepository.save(candidate);

        return "redirect:/admin/start-election";
    }

    // ✅ Delete candidate
    @GetMapping("/delete-candidate/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
        return "redirect:/admin/start-election";
    }

    @GetMapping("/publish-result")
    public String publishResult() {
        return "admin/publish-result";
    }

    @GetMapping("/cease-election")
    public String ceaseElection() {
        return "admin/cease-election";
    }
}
