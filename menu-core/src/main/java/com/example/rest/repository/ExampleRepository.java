package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.model.Example;

public interface ExampleRepository extends JpaRepository<Example, Integer>{

}
