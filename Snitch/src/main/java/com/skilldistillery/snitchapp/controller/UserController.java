package com.skilldistillery.snitchapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	
	//Get all
	@GetMapping("users")
	public List<User> findAllUsers(){
		
		return uSvc.findAllUsers();
	}
	
	
	
	
	@GetMapping("users/{uId}")
	public User findById(@PathVariable("uId") Integer uId, HttpServletResponse response) {
		
		try {
			User user = uSvc.findById(uId);
			if(user==null) {
				response.setStatus(404);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
		
	}
	
	
	
	
	//PutProfile
	
	
	
	//"Delete"User

}
