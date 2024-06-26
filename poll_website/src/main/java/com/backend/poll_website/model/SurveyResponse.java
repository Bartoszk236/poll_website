package com.backend.poll_website.model;

import com.backend.poll_website.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponse {
    private String question;
    private List<Answer> answers;
}
