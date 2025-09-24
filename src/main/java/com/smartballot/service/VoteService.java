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
        // 1️⃣ Check election exists
        Optional<Election> electionOpt = electionRepository.findById(electionId);
        if (electionOpt.isEmpty()) {
            return "Election not found";
        }
        Election election = electionOpt.get();

        // 2️⃣ Check if election is active today
        LocalDate today = LocalDate.now();
        if (!"ACTIVE".equalsIgnoreCase(election.getStatus()) || !today.equals(election.getElectionDate())) {
            return "Election is not active right now";
        }

        // 3️⃣ Check if student already voted in this election
        if (voteRepository.findByStudentIdAndElection(studentId, election).isPresent()) {
            return "You have already voted in this election";
        }

        // 4️⃣ Check candidate exists and belongs to this election
        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);
        if (candidateOpt.isEmpty() || !candidateOpt.get().getElection().getId().equals(electionId)) {
            return "Candidate not found in this election";
        }

        // 5️⃣ Save vote
        Vote vote = new Vote();
        vote.setStudentId(studentId);
        vote.setElection(election);
        vote.setCandidate(candidateOpt.get());
        vote.setCastTime(LocalDateTime.now());

        voteRepository.save(vote);

        return "Vote cast successfully";
    }
}
