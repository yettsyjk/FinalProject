package com.skilldistillery.snitchapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class UserController {
	
	@Autowired 
	private UserService uSvc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong"; 
	}
	
	//Get all
	@GetMapping("users")
	public List<User> findAllUsers(){
		
		return uSvc.findAllUsers();
	}
	
	
	
	//GetByIdAndUserName
	
	@GetMapping("users/{uId}/profile")
	public User findByIdAndUsername(@PathVariable Integer uId, @PathVariable String username) {
		return null;
		
	}
	
	//GetById
	
	
	
	//PostProfile
	
	
	
	//"Delete"User

}
