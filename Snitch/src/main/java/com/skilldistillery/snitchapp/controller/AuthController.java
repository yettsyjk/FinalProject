package com.skilldistillery.snitchapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.services.AuthService;
import com.skilldistillery.snitchapp.services.UserService;

@RestController

@CrossOrigin({ "*", "http://localhost:4210" }) // scripts that come from angular will be authenticated
public class AuthController {
	
	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private UserService uSvc;

	@PostMapping("/register") // allows to sign up/create account
	public User register(
			@RequestBody User user, 
			HttpServletResponse response) {
		if (user == null) {
			response.setStatus(400);
		}
		user = authSvc.register(user);
		user.setPassword(null);
		return user;
		
	}

	@GetMapping("/authenticate")
	public User authenticate(Principal principal) { // checks username and pass
		
		 User user = uSvc.findUserByUsername(principal.getName());
		 user.setPassword(null);
		return user;
	}
}