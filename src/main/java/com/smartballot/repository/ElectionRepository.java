package com.smartballot.repository;

import com.smartballot.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    Optional<Election> findByName(String name);

    // Fetch elections by status (ACTIVE, SCHEDULED, ENDED)
    List<Election> findByStatus(String status);
}
