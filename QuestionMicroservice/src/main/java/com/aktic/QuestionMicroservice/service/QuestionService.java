package com.aktic.QuestionMicroservice.service;

import com.aktic.QuestionMicroservice.model.Question;
import com.aktic.QuestionMicroservice.repository.QuestionRepository;
import com.aktic.QuestionMicroservice.response.QuizResponse;
import com.aktic.QuestionMicroservice.wrapper.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions(String category) {
        if (category != null ) {
            return questionRepository.findByCategory(category);
        } else {
            return questionRepository.findAll();
        }
    }

    public Question getQuestionById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public String deleteQuestion(int id) {
        Question question = questionRepository.findById(id).orElse(null);
        if(question != null) {
            questionRepository.delete(question);
            return "success";
        }

        return "fail";
    }

    public Question editQuestion(int id, Question question) {
        // Fetch the existing question by its id
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + question.getId()));

        // Update the fields
        existingQuestion.setTitle(question.getTitle());
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setBody(question.getBody());
        existingQuestion.setOption1(question.getOption1());
        existingQuestion.setOption2(question.getOption2());
        existingQuestion.setOption3(question.getOption3());
        existingQuestion.setOption4(question.getOption4());
        existingQuestion.setAnswer(question.getAnswer());

        // Save and return the updated question
        return questionRepository.save(existingQuestion);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQuestions) {
        List<Integer> questionIds = questionRepository.findRandomQuestionsByCategory(category, numQuestions);

        return new ResponseEntity<>(questionIds, HttpStatus.OK);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question != null) {
                questionWrappers.add(new QuestionWrapper(question.getId(), question.getTitle(), question.getBody(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
            }
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(List<QuizResponse> responses) {
        int right = 0;
        for (QuizResponse response : responses) {
            Optional<Question> question = questionRepository.findById(response.getId());
            if(question.isPresent()) {
                if(question.get().getAnswer().equals(response.getResponse())) {
                    right++;
                }

            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
