package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.EssayQuestion;
import com.example.myapp.models.Exam;
import com.example.myapp.models.FillInTheBlankQuestion;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.MultipleChoiceQuestion;
import com.example.myapp.models.TrueFalseQuestion;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.EssayQuestionRepository;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.FillInTheBlankQuestionRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.MultipleChoiceQuestionRepository;
import com.example.myapp.repositories.TrueFalseQuestionRepository;
import com.mysql.fabric.xmlrpc.base.Data;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
//	TrueFalseQuestionRepository trueFalseRepository;
//	@Autowired
//	MultipleChoiceQuestionRepository multipleChoiceRepository;
//	@Autowired 
//	EssayQuestionRepository	essayRepository;
//	@Autowired 
//	FillInTheBlankQuestionRepository fillRepository;

	
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamForLesson(@PathVariable("lessonId") int lessonId) {
		System.out.println("lessonID " + lessonId);
		
		Iterable<Exam> data = examRepository.findAll();
		
		List<Exam> result = new ArrayList<>();
		for(Exam exam : data) {
			if (exam.getLesson().getId() == lessonId) {
				result.add(exam);
			}
		}
		return result;
		
//		Optional<Lesson> data = lessonRepository.findById(lessonId);
//		
//		if(data.isPresent()) {
//			List<Widget> result = data.get().getWidgets();
//			List<Widget> finalResult = new ArrayList<>();
//			for(Widget w : result) {
//				if(w.getWidgetType().equals("Exam")) {
//					finalResult.add(w);
//				}
//			}
//			return finalResult;
//		}
//		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;		
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
	
//	@PostMapping("/api/exam/{examId}/essay")
//	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId,
//			@RequestBody EssayQuestion newEssayQuestion) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newEssayQuestion.setExam(exam);
//			return essayRepository.save(newEssayQuestion);
//		}
//		return null;
//	}
//	
//	@PostMapping("/api/exam/{examId}/choice")
//	public MultipleChoiceQuestion createMultipleChoiceQuestion(@PathVariable("examId") int examId,
//			@RequestBody MultipleChoiceQuestion newMultipleChoiceQuestion) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newMultipleChoiceQuestion.setExam(exam);
//			return multipleChoiceRepository.save(newMultipleChoiceQuestion);
//		}
//		return null;
//	}
//	
//	@PostMapping("/api/exam/{examId}/blanks")
//	public FillInTheBlankQuestion createFillIntheBlankQuestion(@PathVariable("examId") int examId,
//			@RequestBody FillInTheBlankQuestion newFillInTheBlankQuestion) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newFillInTheBlankQuestion.setExam(exam);
//			return fillRepository.save(newFillInTheBlankQuestion);
//		}
//		return null;
//	}
//	
//	@PostMapping("/api/exam/{examId}/truefalse")
//	public TrueFalseQuestion TrueFalse(@PathVariable("examId") int examId,
//			@RequestBody TrueFalseQuestion newTrueFalseQuestion) {
//		Optional<Exam> data = examRepository.findById(examId);
//		
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			newTrueFalseQuestion.setExam(exam);
//			return trueFalseRepository.save(newTrueFalseQuestion);
//		}
//		return null;
//	}
	
	
	
	
//	@GetMapping("/api/truefalse/{questionId}")
//	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
//		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}
//	
//	
//	@GetMapping("/api/multi/{questionId}")
//	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
//		Optional<MultipleChoiceQuestion> optional = mutiRepo.findById(questionId);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}


}
