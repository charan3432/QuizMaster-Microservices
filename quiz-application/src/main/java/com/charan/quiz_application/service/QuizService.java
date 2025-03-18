package com.charan.quiz_application.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.charan.quiz_application.dao.QuestionDao;
import com.charan.quiz_application.dao.QuizDao;
import com.charan.quiz_application.model.Question;
import com.charan.quiz_application.model.Quiz;
import com.charan.quiz_application.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz); 
		
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	public Optional<Quiz> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		return quiz;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Quiz quiz = quizDao.findById(id).get();
		
		List<Question> questions = quiz.getQuestions();
		
		int i=0;
		int right=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRight_answer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}
	

}
