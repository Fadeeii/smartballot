package com.smartballot.repository;

import com.smartballot.model.Vote;
import com.smartballot.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    // Find all votes by a student
    List<Vote> findByStudentId(Long studentId);

    // Check if a student has voted in a specific election
    Optional<Vote> findByStudentIdAndElection(Long studentId, Election election);
}
