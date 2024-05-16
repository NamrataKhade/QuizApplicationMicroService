package com.quizapplication.questionservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quizapplication.questionservice.entity.Question;
import com.quizapplication.questionservice.entity.QuestionWrapper;
import com.quizapplication.questionservice.entity.Response;


public interface QuestionService {

	Question saveQuestions(Question question);

	List<Question> getAllQuestions();

	List<Question> getQuestionByCategory(String category);

	ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions);

	ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds);

	

	ResponseEntity<Integer> getScore(List<Response> responses);

	

}
