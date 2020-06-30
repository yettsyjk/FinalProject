package com.skilldistillery.snitchapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Category;
import com.skilldistillery.snitchapp.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public List<Category> findAll() {
	
		return catRepo.findAll();
	}

	@Override
	public Category findByName(String catName) {

		Category foundCategory = catRepo.findByName(catName);
				
//		if(foundCategory)
		
		return foundCategory;
	}
	
}
