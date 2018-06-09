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
import com.example.myapp.models.MultipleChoiceQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.MultipleChoiceQuestionRepository;
import com.example.myapp.repositories.QuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionService {
	
	@Autowired
	MultipleChoiceQuestionRepository multipleChoiceRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceQuestion createMultipleChoiceQuestion(@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceQuestion newMultipleChoiceQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultipleChoiceQuestion.setExam(exam);
			return multipleChoiceRepository.save(newMultipleChoiceQuestion);
		}
		return null;
	}
	
//	@DeleteMapping("/api/essay/{questionId}")
//	public void deleteMultipleChoiceQuestion(@PathVariable("questionId") int questionId) {
//		questionRepository.deleteById(questionId);
//	}

}
