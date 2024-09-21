package com.aktic.QuestionMicroservice.controller;

import com.aktic.QuestionMicroservice.model.Question;
import com.aktic.QuestionMicroservice.response.QuizResponse;
import com.aktic.QuestionMicroservice.service.QuestionService;
import com.aktic.QuestionMicroservice.wrapper.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    private String home() {
        return "Server is running at PORT 8080";
    }

    @GetMapping("/getAllQuestions")
    private ResponseEntity<List<Question>> getAllQuestions(@RequestParam(required = false) String category) {
        return new ResponseEntity<>(questionService.getAllQuestions(category), HttpStatus.OK);
    }

    @GetMapping("/getQuestion/{id}")
    private ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    private ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    private ResponseEntity<String>  deleteQuestion(@PathVariable int id) {
        return new ResponseEntity<>(questionService.deleteQuestion(id), HttpStatus.OK);
    }

    @PutMapping("/editQuestion/{id}")
    private ResponseEntity<Question> editQuestion(@PathVariable int id, @RequestBody Question question) {
        Question updatedQuestion = questionService.editQuestion(id, question);
        if (updatedQuestion != null) {
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/generate")
    private ResponseEntity<List<Integer>>  generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions) {
        return questionService.getQuestionsForQuiz(category, numQuestions);
    }

    @PostMapping("/getQuizQuestions")
    private ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds (@RequestBody List<Integer> questionIds) {
        System.out.println(Arrays.toString(questionIds.toArray()));
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("/getScore")
    private ResponseEntity<Integer> submitQuiz (@RequestBody List<QuizResponse> responses) {
        return questionService.calculateResult(responses);
    }
}
