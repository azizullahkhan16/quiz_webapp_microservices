package com.aktic.QuizMicroservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private int id;
    private String title;
    private String body;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
