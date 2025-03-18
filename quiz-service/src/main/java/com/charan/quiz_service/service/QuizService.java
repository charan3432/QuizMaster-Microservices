package com.charan.quiz_service.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.charan.quiz_service.dao.QuizDao;
import com.charan.quiz_service.feign.QuizInterface;
import com.charan.quiz_service.model.Quiz;
import com.charan.quiz_service.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		
		 List<Integer> questions = quizInterface.getQuestiondsForQuiz(category, numQ).getBody();
		 
		 Quiz quiz = new Quiz();
		 
		 quiz.setTitle(title);
		 quiz.setQuestionsIds(questions);
		 
		 quizDao.save(quiz);
		
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	public Optional<Quiz> getQuizQuestions(Integer id) { 
		Optional<Quiz> quiz = quizDao.findById(id);
		return quiz;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Quiz quiz = quizDao.findById(id).get();
		
//		List<Question> questions = quiz.getQuestions();
//		
//		int i=0;
		int right=0;
//		for(Response response:responses) {
//			if(response.getResponse().equals(questions.get(i).getRight_answer())) {
//				right++;
//			}
//			i++;
//		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}
	

}
