package com.quizapplication.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.questionservice.entity.Question;
import com.quizapplication.questionservice.entity.QuestionWrapper;
import com.quizapplication.questionservice.entity.Response;
import com.quizapplication.questionservice.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
    @Autowired
	private QuestionService questionService;
    
    @Autowired
    Environment environment;

    @PostMapping("/add")
	public Question saveQuestion(@RequestBody Question question ) {
		return this.questionService.saveQuestions(question);
	}
    
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
		return questionService.getAllQuestions();
    	
    }
    
    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
    	return questionService.getQuestionByCategory(category);
    }
    
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
    	return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }
    
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds){
    	System.out.println(environment.getProperty("local.server.port"));
    return	questionService.getQuestionsFromId(questionsIds);
    }
    
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
       return 	questionService.getScore(responses);
    }
    
    
}
