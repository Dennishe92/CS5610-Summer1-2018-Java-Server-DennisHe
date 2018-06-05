package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.MultipleChoiceQuestion;

public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer>{

}
