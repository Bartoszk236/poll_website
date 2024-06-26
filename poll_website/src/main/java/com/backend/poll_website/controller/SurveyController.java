package com.backend.poll_website.controller;

import com.backend.poll_website.entity.Survey;
import com.backend.poll_website.model.ResultsResponse;
import com.backend.poll_website.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping("/create")
    public ResponseEntity<?> createSurvey(@RequestBody Survey survey){
        Survey newSurvey = surveyService.createSurvey(survey);
        return ResponseEntity.status(200).body("Survey created successful");
    }

    @GetMapping("/results/{survey_id}")
    public ResponseEntity<?> resultSurvey(@PathVariable Long survey_id){
        return ResponseEntity.status(200).body(surveyService.getResults(survey_id));
    }
    @GetMapping("/{survey_id}")
    public ResponseEntity<?> getSurvey(@PathVariable Long survey_id){
        return ResponseEntity.status(200).body(surveyService.getSurvey(survey_id));
    }
}
