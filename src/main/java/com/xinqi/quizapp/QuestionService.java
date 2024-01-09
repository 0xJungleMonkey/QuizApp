package com.xinqi.quizapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
        private final QuestionRepository questionRepository;

        @Autowired
        public QuestionService(QuestionRepository questionRepository) {
            this.questionRepository = questionRepository;
        }

        public Question saveQuestion(Question question) {
            return questionRepository.save(question);
        }//user add questions from frontend

        public List<Question> getAllQuestion() {
            return questionRepository.findAll();
        }//get all the questions
        public Optional<Question> getQuestionById(long id){
            return questionRepository.findById(id);
        }//get question by id

    public void saveQuestionsFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array into a list of Question objects
            List<Question> questions = objectMapper.readValue(json, new TypeReference<List<Question>>() {});

            // Save the list of questions to the database
            questionRepository.saveAll(questions);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }//insert all the questions parsed from trivia api response.

    public void saveQuestionFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Assuming you have a class representing the JSON structure
            Question question = objectMapper.readValue(json, Question.class);

            // Process the Trivia object and save questions
            questionRepository.save(question);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }





    public void printTableContent() {
        List<Question> questions = questionRepository.findAll();

        for (Question question : questions) {
            System.out.println("ID: " + question.getId());
            System.out.println("Type: " + question.getType());
            System.out.println("Difficulty: " + question.getDifficulty());
            System.out.println("Category: " + question.getCategory());
            System.out.println("Question: " + question.getQuestion_body());
            System.out.println("Correct Answer: " + question.getCorrect_answer());
            System.out.println("Incorrect Answers: " + question.getIncorrect_answers());
            System.out.println("----------------------------------------");
        }
    }


    }


