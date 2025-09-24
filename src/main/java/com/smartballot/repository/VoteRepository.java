package com.smartballot.repository;

import com.smartballot.model.Vote;
import com.smartballot.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    // Check if a student has already voted in a specific election
    Optional<Vote> findByStudentIdAndElection(Long studentId, Election election);

    // List all votes by a student (optional, if you need it)
    List<Vote> findByStudentId(Long studentId);
}
