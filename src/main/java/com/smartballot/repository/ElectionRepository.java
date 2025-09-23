package com.smartballot.repository;

import com.smartballot.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    // Extra custom queries (if needed) will go here
}
