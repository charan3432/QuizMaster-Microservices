package com.charan.quiz_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDto {
	String categoryName;
	Integer numQuestions;
	String title;
}
 