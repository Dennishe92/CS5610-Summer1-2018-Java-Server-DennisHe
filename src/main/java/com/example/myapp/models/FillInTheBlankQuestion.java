package com.example.myapp.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question{

	private String correctAnswer;

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
}
