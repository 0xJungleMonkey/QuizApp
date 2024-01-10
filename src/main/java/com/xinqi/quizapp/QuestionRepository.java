package com.xinqi.quizapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
    List<Question> findByCategoryAndDifficulty(String category, String difficulty);

}
