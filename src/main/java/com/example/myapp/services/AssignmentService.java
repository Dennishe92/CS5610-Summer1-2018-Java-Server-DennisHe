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

import com.example.myapp.models.Assignment;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.AssignmentRepository;
import com.example.myapp.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignment() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public List<Assignment> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		System.out.println("lessonID " + lessonId);
		
		Iterable<Assignment> data = assignmentRepository.findAll();
		
		List<Assignment> result = new ArrayList<>();
		for(Assignment a : data) {
			if(a.getLesson().getId() == lessonId) {
				result.add(a);
			}
		}
		return result;
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@PathVariable("lessonId") int lessonId,
			@RequestBody Assignment newAssignment) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newAssignment.setLesson(lesson);
			return assignmentRepository.save(newAssignment);		
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}

}
