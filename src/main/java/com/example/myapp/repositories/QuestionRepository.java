package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
