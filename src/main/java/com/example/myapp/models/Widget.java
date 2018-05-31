package com.example.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget implements Comparable<Widget>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String text;
	private int widgetOrder; 
	private String widgetType;
	private String name;
//	private String style;
//	private String width;
//	private String height;
	private String listType;
	

	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public int getWidgetOrder() {
		return widgetOrder;
	}
	public void setWidgetOrder(int widgetOrder) {
		this.widgetOrder = widgetOrder;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
//	public String getStyle() {
//		return style;
//	}
//	public void setStyle(String style) {
//		this.style = style;
//	}
//	public String getWidth() {
//		return width;
//	}
//	public void setWidth(String width) {
//		this.width = width;
//	}
//	public String getHeight() {
//		return height;
//	}
//	public void setHeight(String height) {
//		this.height = height;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	@Override
	public int compareTo(Widget other) {
		return this.widgetOrder - other.widgetOrder;
	}
	
	
	
	
	
	
	

}
