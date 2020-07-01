package com.skilldistillery.snitchapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class UserController {

	@Autowired
	private UserService uSvc;

	// Get all
	@GetMapping("users")
	public List<User> findAllUsers() {

		return uSvc.findAllUsers();
	}

	@GetMapping("users/{uId}")
	public User findById(@PathVariable("uId") Integer uId, HttpServletResponse response) {

		try {
			User user = uSvc.findById(uId);
			if (user == null) {
				response.setStatus(404);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}

	}

	// PutProfile
	@PutMapping("users/{uId}")
	public User updateUser(@PathVariable("uId") Integer uId, @RequestBody User user, HttpServletResponse response) {
		try {
			user = uSvc.updateProfile(uId, user);
			if (user == null) {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			user = null;
		}

		return user;
	}

	// "Delete"User
	@DeleteMapping("users/{uId}")
	public void disableUser(@PathVariable("uId") Integer uId, HttpServletResponse response) {
		try {
			if (uSvc.remove(uId)) {
				response.setStatus(204);
			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
	
	@GetMapping("users/username")
	public User findUserByUsername(Principal principal,
			HttpServletResponse response) {

		try {
			User user = uSvc.findUserByUsername(principal.getName());
			if (user == null) {
				response.setStatus(404);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}

	}
	

}
