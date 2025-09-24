package com.smartballot.repository;

import com.smartballot.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    // Check if a student has already voted in an election
    Optional<Vote> findByStudentIdAndElectionId(Long studentId, Long electionId);

    // Count votes for a candidate
    long countByCandidateId(Long candidateId);
}
