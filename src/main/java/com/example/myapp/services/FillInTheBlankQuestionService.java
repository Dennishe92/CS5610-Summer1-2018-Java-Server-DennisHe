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
import com.example.myapp.models.FillInTheBlankQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInTheBlankQuestionRepository;
import com.example.myapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlankQuestionService {
	
	@Autowired
	FillInTheBlankQuestionRepository fillRepository;

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlankQuestion createFillIntheBlankQuestion(@PathVariable("examId") int examId,
			@RequestBody FillInTheBlankQuestion newFillInTheBlankQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newFillInTheBlankQuestion.setExam(exam);
			return fillRepository.save(newFillInTheBlankQuestion);
		}
		return null;
	}
	
//	@DeleteMapping("/api/essay/{questionId}")
//	public void deleteFillInTheBlankQuestion(@PathVariable("questionId") int questionId) {
//		questionRepository.deleteById(questionId);
//	}
	
	
}
