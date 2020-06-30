package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.Category;

public interface CategoryService {

	List <Category> findAll();
	
	Category findByName(String catName);
	
}
