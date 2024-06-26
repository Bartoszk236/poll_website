package com.backend.poll_website.service;

import com.backend.poll_website.entity.Answer;
import com.backend.poll_website.entity.Survey;
import com.backend.poll_website.model.ResultsResponse;
import com.backend.poll_website.model.SurveyResponse;
import com.backend.poll_website.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ResultsResponse resultsResponse;

    public Survey createSurvey(Survey survey){
        survey.setCreationTime(LocalDateTime.now());
        survey.setDeletionTime(LocalDateTime.now().plusHours(48));
        return surveyRepository.save(survey);
    }

    public List<ResultsResponse> getResults(Long surveyId){
        List<ResultsResponse> responses = new ArrayList<>();
        Survey survey = surveyRepository.getReferenceById(surveyId);
        List<Answer> answers = survey.getAnswers();
        for (int i = 0; i < answers.size(); i++){
            ResultsResponse response = new ResultsResponse();
            response.setAnswer(answers.get(i).getAnswer());
            response.setVotes(answers.get(i).getVotes().size());
            responses.add(response);
        }
        return responses;
    }

    public SurveyResponse getSurvey(Long id){
        SurveyResponse surveyResponse = new SurveyResponse();
        Survey survey = surveyRepository.getReferenceById(id);
        surveyResponse.setQuestion(survey.getQuestion());
        surveyResponse.setAnswers(survey.getAnswers());
        return surveyResponse;
    }

    @Scheduled(fixedRate = 1000)
    public void removeExpiredSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        List<Survey> expiredSurveys = surveys.stream()
                .filter(survey -> survey.getDeletionTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        if (!expiredSurveys.isEmpty()) {
            surveyRepository.deleteAll(expiredSurveys);
        }
    }
}
