package com.smartballot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String party;

    @Column(nullable = false)
    private String electionDate; // keep as String for simplicity

    @Column(nullable = false)
    private String electionTime; // placeholder time

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    // Constructors
    public Candidate() {}
    public Candidate(String name, String party, String electionDate, String electionTime) {
        this.name = name;
        this.party = party;
        this.electionDate = electionDate;
        this.electionTime = electionTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }

    public String getElectionDate() { return electionDate; }
    public void setElectionDate(String electionDate) { this.electionDate = electionDate; }

    public String getElectionTime() { return electionTime; }
    public void setElectionTime(String electionTime) { this.electionTime = electionTime; }

    public Election getElection() { return election; }
    public void setElection(Election election) { this.election = election; }
}
