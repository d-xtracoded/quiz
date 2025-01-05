package com.prime.primequiz.controller;

import com.prime.primequiz.model.Question;
import com.prime.primequiz.model.QuestionWrapper;
import com.prime.primequiz.model.Quiz;
import com.prime.primequiz.model.Response;
import com.prime.primequiz.repository.QuizDao;
import com.prime.primequiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    QuizDao quizDao;
    @PostMapping("/create")
    public ResponseEntity <String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
//http://localhost:8080/quiz/create?category=java&numQ=3&title=JQuiz
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        ////http://localhost:8080/quiz/get/1
        return quizService.getQuestions(id);
    }

@PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
////http://localhost:8080/quiz/submit/1   submit quiz1
        return quizService.calculateResult(id,responses);
    }



}
