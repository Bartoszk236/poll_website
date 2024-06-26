package com.backend.poll_website.service;

import com.backend.poll_website.entity.Answer;
import com.backend.poll_website.entity.Survey;
import com.backend.poll_website.entity.Vote;
import com.backend.poll_website.repository.AnswerRepository;
import com.backend.poll_website.repository.SurveyRepository;
import com.backend.poll_website.repository.VoteRepository;
import com.backend.poll_website.security.IpAddress;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private IpAddress ipAddress;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Vote addVote(Long answerId, HttpServletRequest request, Long surveyId){
        String ip = ipAddress.getIpAddress(request);
        Survey survey = surveyRepository.getReferenceById(surveyId);
        Answer answer = answerRepository.getReferenceById(answerId);

        if (!checkMultiplyVote(ip, survey, answer)){
            Vote vote = new Vote();
            vote.setIpAddress(ip);
            vote.setAnswer(answer);
            voteRepository.save(vote);
            return vote;
        }
        return null;
    }

    public boolean checkMultiplyVote(String ip, Survey survey, Answer answer){
        List<Answer> answers = survey.getAnswers();
        for (int i = 0; i < answers.size(); i++){
            List<Vote> votes = answers.get(i).getVotes();
            for (int j = 0; j < votes.size(); j++){
                if (votes.get(j).getIpAddress().equals(ip)){
                    return true;
                }
            }
        }
        return false;
    }


}
