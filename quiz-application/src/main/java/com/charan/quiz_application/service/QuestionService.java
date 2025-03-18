package com.charan.quiz_application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.charan.quiz_application.dao.QuestionDao;
import com.charan.quiz_application.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>>  getAllQuestions() {
		
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		
		
	}
	
//	public ResponseEntity<List<Question>> getQuestionsByCategoryAndLevel(String category, String level) {
//		try {
//			List<Question> questions = questionDao.findByCategoryAndDifficultylevel(category, level);
//			return new ResponseEntity<>(questions, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
//		
//	}

	public ResponseEntity<String> addQuestion(Question question) {
		
		
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Failed to add the question", HttpStatus.BAD_REQUEST);
		
		
	}

	public ResponseEntity<String> deleteQuestionById(Integer id) {
		if(questionDao.existsById(id)) {
			questionDao.deleteById(id);
			return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<String>("Question with id "+id+" Not found", HttpStatus.NOT_FOUND);
	}

}
