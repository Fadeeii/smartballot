package com.smartballot.controller;

import com.smartballot.model.Candidate;
import com.smartballot.model.Election;
import com.smartballot.repository.CandidateRepository;
import com.smartballot.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @GetMapping("/start-election")
    public String startElectionPage(Model model) {
        List<Election> elections = electionRepository.findAll();
        model.addAttribute("elections", elections);
        return "admin/start-election";
    }

    @PostMapping("/start-election")
    public String addCandidate(@RequestParam("candidate_name") String candidateName,
                               @RequestParam("party_name") String partyName,
                               @RequestParam("election_name") String electionName,
                               @RequestParam("election_date") String electionDate) {

        Election election = electionRepository.findByName(electionName)
                .orElseGet(() -> {
                    Election e = new Election();
                    e.setName(electionName);
                    e.setElectionDate(LocalDate.parse(electionDate));
                    e.setStatus("SCHEDULED");
                    return electionRepository.save(e);
                });

        Candidate candidate = new Candidate();
        candidate.setName(candidateName);
        candidate.setParty(partyName);
        candidate.setElectionDate(electionDate);
        candidate.setElectionTime("10:00");
        candidate.setElection(election);

        candidateRepository.save(candidate);
        return "redirect:/admin/start-election";
    }

    @GetMapping("/delete-candidate/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
        return "redirect:/admin/start-election";
    }

    @PostMapping("/start-election/{id}")
    public String startElection(@PathVariable Long id) {
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid election ID: " + id));
        election.setStatus("ACTIVE");
        election.setStartTime(LocalTime.now());
        electionRepository.save(election);
        return "redirect:/admin/start-election";
    }

    @PostMapping("/stop-election/{id}")
    public String stopElection(@PathVariable Long id) {
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid election ID: " + id));
        election.setStatus("ENDED");
        election.setEndTime(LocalTime.now());
        electionRepository.save(election);
        return "redirect:/admin/start-election";
    }
}
