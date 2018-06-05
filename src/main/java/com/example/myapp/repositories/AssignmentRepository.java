package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.Assignment;

public interface AssignmentRepository extends CrudRepository <Assignment, Integer> {

}
