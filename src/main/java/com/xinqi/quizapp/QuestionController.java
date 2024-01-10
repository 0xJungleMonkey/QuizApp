package com.xinqi.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Question")
public class QuestionController {

    private final QuestionService QuestionService;

    @Autowired
    public QuestionController(QuestionService QuestionService) {
        this.QuestionService = QuestionService;
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question Question) {
        return QuestionService.saveQuestion(Question);
    }

    @GetMapping
    public List<Question> getAllQuestion() {
        return QuestionService.getAllQuestion();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getOneQuestion(@PathVariable long id) {
        Optional<Question> question = QuestionService.getQuestionById(id);

        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());


    }

    @GetMapping("/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return QuestionService.getQuestionsByCategory(category);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestionsByCategoryAndDifficulty(
            @RequestParam(name = "category") String category,
            @RequestParam(name = "difficulty") String difficulty) {
        List<Question> questions = QuestionService.findQuestionsByCategoryAndDifficulty(category, difficulty);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }




}
