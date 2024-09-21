package com.aktic.QuizMicroservice.controller;


import com.aktic.QuizMicroservice.model.Quiz;
import com.aktic.QuizMicroservice.response.QuizResponse;
import com.aktic.QuizMicroservice.service.QuizService;
import com.aktic.QuizMicroservice.wrapper.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/createQuiz")
    private ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQuestions, @RequestParam String title) {
        return quizService.createQuiz(category, numQuestions, title);
    }

    @GetMapping("/getQuestions/{id}")
    private ResponseEntity<List<QuestionWrapper>> getQuizQuestions (@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    private ResponseEntity<Integer> submitQuiz (@PathVariable int id, @RequestBody List<QuizResponse> responses) {
        return quizService.calculateResult(id, responses);
    }
}
