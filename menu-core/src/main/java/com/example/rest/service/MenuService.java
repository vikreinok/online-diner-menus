package com.example.rest.service;

import java.util.List;

import com.example.rest.exception.MenuNotFoundException;
import com.example.rest.model.Menu;

public interface MenuService {

	public Menu create(Menu menu);
	public Menu get(Integer id);
	public List<Menu> getAll();
	public Menu update(Menu sp) throws MenuNotFoundException;
	public Menu delete(Integer id) throws MenuNotFoundException;
}
