package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.Question;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(
			@PathVariable("examId") int examId) {
		
		Optional<Widget> data = widgetRepository.findById(examId);
		if(data.isPresent()) {
			Widget widget = data.get();
			Exam exam = (Exam)widget;
			List<Question> result = exam.getQuestions();
			return result;
		}
		return null;		
	}
	
	@DeleteMapping("/api/essay/{questionId}")
	public void deleteQuestionById(@PathVariable("questionId") int questionId) {
		questionRepository.deleteById(questionId);
	}
	
}
