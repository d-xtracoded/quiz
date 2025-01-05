package com.prime.primequiz.controller;

import com.prime.primequiz.model.Question;
import com.prime.primequiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getallQuestion(){

        return questionService.getAllQuestions();
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<List<Question>> getbyCategory(@PathVariable String category){

        return  questionService.getQuestionsByCategory(category);
    }
    // get by level

    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
