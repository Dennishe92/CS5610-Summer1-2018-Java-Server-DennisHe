package com.example.myapp.models;

import javax.persistence.Entity;

@Entity
public class EssayQuestion extends Question {
	
	private String essayAnswer;

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	
	
}
