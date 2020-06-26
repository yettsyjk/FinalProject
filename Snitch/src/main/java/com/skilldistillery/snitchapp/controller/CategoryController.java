package com.skilldistillery.snitchapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.Category;
import com.skilldistillery.snitchapp.services.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class CategoryController {

	@Autowired
	private CategoryService catSvc;
	
	
	@GetMapping("categories")
	public List <Category> index(){
		
		return catSvc.findAll();
	}
	
}
