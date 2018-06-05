package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.TrueFalseQuestion;

public interface TrueFalseQuestionRepository extends CrudRepository<TrueFalseQuestion, Integer>{

}
