package com.backend.poll_website.controller;

import com.backend.poll_website.entity.Answer;
import com.backend.poll_website.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/add/{survey_id}")
    public ResponseEntity<?> addAnswer(@RequestBody Answer answer, @PathVariable Long survey_id){
        answerService.addAnswer(answer, survey_id);
        return ResponseEntity.status(200).body("Answer added successful");
    }
}
