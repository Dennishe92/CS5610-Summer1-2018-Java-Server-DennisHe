package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.TrueFalseQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepository;
import com.example.myapp.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueFalseQuestionService {

	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueFalseQuestion TrueFalse(@PathVariable("examId") int examId,
			@RequestBody TrueFalseQuestion newTrueFalseQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueFalseQuestion.setExam(exam);
			return trueFalseRepository.save(newTrueFalseQuestion);
		}
		return null;
	}
	
//	@DeleteMapping("/api/essay/{questionId}")
//	public void deleteTrueFalseQuestion(@PathVariable("questionId") int questionId) {
//		questionRepository.deleteById(questionId);
//	}
	
}
