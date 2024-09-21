package com.aktic.QuizMicroservice.feign;

import com.aktic.QuizMicroservice.response.QuizResponse;
import com.aktic.QuizMicroservice.wrapper.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("Question-Microservice")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("/question/getQuizQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds (@RequestBody List<Integer> questionIds);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> submitQuiz (@RequestBody List<QuizResponse> responses);
}
