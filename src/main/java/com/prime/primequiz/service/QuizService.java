package com.prime.primequiz.service;

import com.prime.primequiz.model.Question;
import com.prime.primequiz.model.QuestionWrapper;
import com.prime.primequiz.model.Quiz;
import com.prime.primequiz.model.Response;
import com.prime.primequiz.repository.QuestionDao;
import com.prime.primequiz.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // get the questions, number and category

        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);
        // create Quiz here
        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quiz.setTitle(title);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsfrmDB= quiz.get().getQuestions();
        List<QuestionWrapper> questionfortaker= new ArrayList<>();// empty array to save question
        for (Question q: questionsfrmDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionfortaker.add(qw);
        }

        //return ResponseEntity.ok(questionfortaker);
        return new ResponseEntity<>(questionfortaker,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> alluserresponses) {
        Quiz quiz= quizDao.findById(id).get(); // get ids of the quiz questions
        List<Question> questionsfrmDB=quiz.getQuestions(); // get QA from DB
        int right=0;
        int i=0;
        for (Response userresponse: alluserresponses){
            if (userresponse.getResponse().equals(questionsfrmDB.get(i).getRightanswer()));
            right++;
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }




}
