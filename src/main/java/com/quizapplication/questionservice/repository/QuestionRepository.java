package com.quizapplication.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizapplication.questionservice.entity.Question;


//@Repository


public interface QuestionRepository extends JpaRepository<Question, Integer> {
//public interface QuestionRepository extends MongoRepository<Question, Integer> {
	List<Question> findByCategory(String category);
	
//	@Query(value="Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ" ,nativeQuery=true)
//
//	List<Question> findRandomQuestionByCategory(String category, int numQ);
	
	
	  @Query(value =
	  "SELECT q.question_Id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ"
	  , nativeQuery = true) List<Integer>
	  findRandomQuestionByCategory(@Param("category") String
	  category, @Param("numQ") int numQ);
	 

}
