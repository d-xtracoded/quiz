package com.prime.primequiz.controller;

import com.prime.primequiz.model.Question;
import com.prime.primequiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getallQuestion(){

        return questionService.getAllQuestions();
    }

    @GetMapping("/getByCategory/{category}")
    public List<Question> getbyCategory(@PathVariable String category){

        return  questionService.getQuestionsByCategory(category);
    }
    // get by level

    @PostMapping("/addquestion")
    public String addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return "Question Added  successfully";
    }

}
