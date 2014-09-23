package com.example.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rest.exception.ExampleNotFoundException;
import com.example.rest.model.Example;
import com.example.rest.repository.ExampleRepository;

@Service
@Transactional(rollbackFor=ExampleNotFoundException.class)
public class ExampleServiceImpl implements ExampleService {
	
	@Autowired
	private ExampleRepository exampleRepository;

	@Override
	public Example create(Example sp) {
		return exampleRepository.save(sp);
	}

	@Override
	public Example get(Integer id) {
		return exampleRepository.findOne(id);
	}

	@Override
	public List<Example> getAll() {
		return exampleRepository.findAll();
	}

	@Override
	public Example update(Example sp) throws ExampleNotFoundException {
		Example exampleToUpdate = get(sp.getId());
		if (exampleToUpdate == null)
			throw new ExampleNotFoundException(sp.getId().toString());
		exampleToUpdate.update(sp);
		return exampleToUpdate;
	}

	@Override
	public Example delete(Integer id) throws ExampleNotFoundException {
		Example example = get(id);
		if (example == null)
			throw new ExampleNotFoundException(id.toString());
		exampleRepository.delete(id);
		return example;
	}

}
