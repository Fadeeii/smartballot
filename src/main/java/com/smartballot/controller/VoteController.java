package com.smartballot.controller;

import com.smartballot.service.VoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    // POST: /votes/cast?studentId=1&electionId=2&candidateId=3
    @PostMapping("/cast")
    public String castVote(@RequestParam Long studentid,
                           @RequestParam Long electionid,
                           @RequestParam Long candidateid) {
        return voteService.castVote(studentid, electionid, candidateid);
    }
}
