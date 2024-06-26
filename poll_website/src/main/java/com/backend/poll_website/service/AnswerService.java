package com.backend.poll_website.service;

import com.backend.poll_website.entity.Answer;
import com.backend.poll_website.repository.AnswerRepository;
import com.backend.poll_website.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public Answer addAnswer(Answer answer, Long surveyId){
        Answer newAnswer = new Answer();
        newAnswer.setAnswer(answer.getAnswer());
        newAnswer.setSurvey(surveyRepository.getReferenceById(surveyId));
        return answerRepository.save(newAnswer);
    }
}
