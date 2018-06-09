package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.EssayQuestion;
import com.example.myapp.models.Exam;
import com.example.myapp.repositories.EssayQuestionRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayQuestionService {
	
	@Autowired
	EssayQuestionRepository essayRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId,
			@RequestBody EssayQuestion newEssayQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssayQuestion.setExam(exam);
			return essayRepository.save(newEssayQuestion);
		}
		return null;
	}
	
//	@DeleteMapping("/api/essay/{questionId}")
//	public void deleteEssayQuestion(@PathVariable("questionId") int questionId) {
//		questionRepository.deleteById(questionId);
//	}

}
