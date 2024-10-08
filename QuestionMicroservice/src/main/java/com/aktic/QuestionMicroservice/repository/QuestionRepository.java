package com.aktic.QuestionMicroservice.repository;

import com.aktic.QuestionMicroservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id from question q where q.category = :category order by random() limit :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQuestions);
}
