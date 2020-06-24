package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.User;

public interface UserService {


	User updateProfile(Integer userId, User user); //String username(might have to use)
	User findById(Integer userId);
	User findUserByUsername(String username);
	List <User> findAllUsers();
	Boolean remove(Integer userId);
	
}


