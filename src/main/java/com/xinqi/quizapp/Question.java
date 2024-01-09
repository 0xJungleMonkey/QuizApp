package com.xinqi.quizapp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
@Entity
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("category")
    private String category;

    @JsonProperty("question")
    private String question_body;
    @JsonProperty("correct_answer")
    private String correct_answer;
    @JsonProperty("incorrect_answers")
    private List<String> incorrect_answers;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }
}
