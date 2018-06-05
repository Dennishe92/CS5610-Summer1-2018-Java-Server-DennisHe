package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.EssayQuestion;

public interface EssayQuestionRepository extends CrudRepository<EssayQuestion, Integer>{

}
