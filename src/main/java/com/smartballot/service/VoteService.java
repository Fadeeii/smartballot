package com.smartballot.service;

import com.smartballot.model.Vote;
import com.smartballot.model.Election;
import com.smartballot.model.Candidate;
import com.smartballot.repository.VoteRepository;
import com.smartballot.repository.ElectionRepository;
import com.smartballot.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public VoteService(VoteRepository voteRepository,
                       ElectionRepository electionRepository,
                       CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
    }

    public String castVote(Long studentId, Long electionId, Long candidateId) {
        // Check election exists
        Optional<Election> electionOpt = electionRepository.findById(electionId);
        if (electionOpt.isEmpty()) {
            return "Election not found";
        }
        Election election = electionOpt.get();

        // Check if election is active on its electionDate
        LocalDate today = LocalDate.now();
        if (!today.equals(election.getElectionDate()) || !"ACTIVE".equals(election.getStatus())) {
            return "Election is not active right now";
        }

        // Check if student already voted in this election
        if (voteRepository.findByStudentIdAndElectionId(studentId, electionId).isPresent()) {
            return "You have already voted in this election";
        }

        // Check candidate exists
        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);
        if (candidateOpt.isEmpty()) {
            return "Candidate not found";
        }

        // Save vote
        Vote vote = new Vote();
        vote.setStudentId(studentId);
        vote.setElection(election);
        vote.setCandidate(candidateOpt.get());
        vote.setCastTime(LocalDateTime.now());

        voteRepository.save(vote);

        return "Vote cast successfully";
    }
}
