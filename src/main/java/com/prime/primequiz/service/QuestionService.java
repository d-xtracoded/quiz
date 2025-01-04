package com.prime.primequiz.service;

import com.prime.primequiz.model.Question;
import com.prime.primequiz.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll() ;
    }

    public List<Question> getQuestionsByCategory(String category) {

        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Success";
    }
}
