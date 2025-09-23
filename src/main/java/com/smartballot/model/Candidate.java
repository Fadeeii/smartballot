package com.smartballot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String candidateName;

    @Column(nullable = false)
    private String partyName;

    // --- RELATIONSHIP: Many candidates belong to one election ---
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getPartyName() { return partyName; }
    public void setPartyName(String partyName) { this.partyName = partyName; }

    public Election getElection() { return election; }
    public void setElection(Election election) { this.election = election; }
}
