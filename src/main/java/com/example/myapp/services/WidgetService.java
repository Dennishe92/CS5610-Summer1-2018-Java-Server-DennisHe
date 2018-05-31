package com.example.myapp.services;

import java.util.Collections;
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

import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.WidgetRepository;

import beans.WidgetJsonBean;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable("lessonId") int lessonId,
			@RequestBody Widget newWidget) {
		
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newWidget.setLesson(lesson);
			return widgetRepository.save(newWidget);
		}
		return null;		
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId){
		widgetRepository.deleteById(widgetId);
	}
	
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
//	@PostMapping("/api/lesson/{lessonId}/widget/save")
//	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
//		widgetRepository.deleteAll();
//		System.out.println(widgets.toString());
//		for(Widget widget: widgets) {
//			System.out.println(widget.getLesson());
//			widgetRepository.save(widget);
//		}
//	}
	
	// We need to use this 
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId) {
		
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			List<Widget> result = lesson.getWidgets();
			Collections.sort(result);
			return result;
			//return lesson.getWidgets();
		}
		return null;		
	}
	
	// We need to use this
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveAllWidgetsForLesson(@PathVariable("lessonId") int lessonId, 
			@RequestBody List<Widget> widgets) {
		//System.out.println("lessonID " + lessonId);
		Optional <Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
//			Lesson old = data.get();
			List<Widget> oldList = data.get().getWidgets();
//			for(Widget w : oldList) {
//				widgetRepository.delete(w);
//				}
			widgetRepository.deleteAll(oldList);
		
//			Lesson lesson = data.get();
			for(Widget widget: widgets) {
				widget.setLesson(data.get());
			}
			widgetRepository.saveAll(widgets);
		}
	}
	
	
	

}
	