package com.quizapplication.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.quizapplication.questionservice.entity.Question;
import com.quizapplication.questionservice.entity.QuestionWrapper;
import com.quizapplication.questionservice.entity.Response;
import com.quizapplication.questionservice.repository.QuestionRepository;


@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question saveQuestions(Question question) {
		return questionRepository.save(question);
    
	}

	@Override
	public List<Question> getAllQuestions() {
		
		return questionRepository.findAll();
	}

	@Override
	public List<Question> getQuestionByCategory(String category) {
		
		return questionRepository.findByCategory(category);
	}

	@Override
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions = questionRepository.findRandomQuestionByCategory(categoryName, numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds) {
		
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		
		
		for(Integer id:questionsIds) {
			questions.add(questionRepository.findById(id).get());
		}
		
		for(Question question:questions) {
		QuestionWrapper wrapper=new QuestionWrapper();
		wrapper.setQuestionId(question.getQuestionId());
		wrapper.setQuestionTitle(question.getQuestionTitle()); 	
		wrapper.setOption1(question.getOption1());
		wrapper.setOption2(question.getOption1());
		wrapper.setOption3(question.getOption3());
		wrapper.setOption4(question.getOption4());
		wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	
	}

	@Override
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
        
        int right=0;
      
        for(Response response:responses) {
        	Question question = questionRepository.findById(response.getId()).get();
        	if(response.getResponse().equals(question.getRightAnswer()))
        		right++;
        	
        }
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
