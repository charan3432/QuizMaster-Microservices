package com.charan.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.charan.question_service.dao.QuestionDao;
import com.charan.question_service.model.Question;
import com.charan.question_service.model.QuestionWrapper;
import com.charan.question_service.model.Response;

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

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(
										String categoryName, 
										Integer numQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
		
		return new ResponseEntity<List<Integer>>(questions, HttpStatus.OK);
	}
	

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id:questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question question:questions) {
			 QuestionWrapper wrapper = new QuestionWrapper();
			 wrapper.setId(question.getId());
			 wrapper.setQuestion_title(question.getQuestion_title());
			 wrapper.setOption1(question.getOption1());
			 wrapper.setOption2(question.getOption2());
			 wrapper.setOption3(question.getOption3());
			 wrapper.setOption4(question.getOption4());
			 wrappers.add(wrapper);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		 
		int right=0;
		for(Response response:responses) {
			
			Question question = questionDao.findById(response.getId()).get();
			
			if(response.getResponse().equals(question.getRight_answer())) {
				right++;
			}
 		}
		
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}

}
