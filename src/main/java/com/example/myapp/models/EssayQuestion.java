package com.example.myapp.models;

import javax.persistence.Entity;

@Entity
public class EssayQuestion extends Question {
	
	private String paragraph;

	public String getEssayAnswer() {
		return paragraph;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.paragraph = essayAnswer;
	}
	
	
}
