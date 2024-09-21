package com.aktic.QuizMicroservice.service;

import com.aktic.QuizMicroservice.feign.QuizInterface;
import com.aktic.QuizMicroservice.wrapper.QuestionWrapper;
import com.aktic.QuizMicroservice.model.Quiz;
import com.aktic.QuizMicroservice.repository.QuizRepository;
import com.aktic.QuizMicroservice.response.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<Quiz> createQuiz(String category, int numQuestions, String title) {
        List<Integer> questionIds = quizInterface.generateQuestionsForQuiz(category, numQuestions).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
         return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            List<Integer> quizQuestionIds = quiz.get().getQuestionIds();

            return quizInterface.getQuestionsFromIds(quizQuestionIds);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> calculateResult (int id, List<QuizResponse> responses){
        int right = quizInterface.submitQuiz(responses).getBody();
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
