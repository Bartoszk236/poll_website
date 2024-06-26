package com.backend.poll_website.controller;

import com.backend.poll_website.entity.Vote;
import com.backend.poll_website.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/add/{survey_id}/{answer_id}")
    public ResponseEntity<?> addVote(@PathVariable Long answer_id, HttpServletRequest request, @PathVariable Long survey_id){
        Vote vote = voteService.addVote(answer_id, request, survey_id);
        if (vote == null) return ResponseEntity.status(400).body("You can't vote again");
        else return ResponseEntity.status(200).body("Vote successful");

    }
}
