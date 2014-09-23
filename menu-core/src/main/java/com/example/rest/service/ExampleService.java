package com.example.rest.service;

import java.util.List;

import com.example.rest.exception.ExampleNotFoundException;
import com.example.rest.model.Example;

public interface ExampleService {
	
	public Example create(Example sp);
	public Example get(Integer id);
	public List<Example> getAll();
	public Example update(Example sp) throws ExampleNotFoundException;
	public Example delete(Integer id) throws ExampleNotFoundException;

}
