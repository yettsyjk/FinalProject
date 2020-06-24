package com.skilldistillery.snitchapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepo;
	
	@Override
	public List<User> findAllUsers() {
		
		return uRepo.findAll();
	}
	
	@Override
	public User findById(Integer userId) {
		
		
		return uRepo.findById(userId).get();
	}
	
	
	@Override
	public User updateProfile(Integer userId, User user) {
	Optional <User> optUser = uRepo.findById(userId);
	
	if(optUser.isPresent()) {
		User updatedUser = optUser.get();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setUsername(user.getUsername());
		updatedUser.setPassword(user.getPassword());
		return uRepo.saveAndFlush(updatedUser);
	}
		return null;
	}


	@Override
	public Boolean remove(Integer userId) {
		boolean deleted = false;
		Optional <User> deleteUser = uRepo.findById(userId);
		
		if(deleteUser.isPresent() && deleteUser.get().getEnabled() == true) {
			deleteUser.get().setEnabled(false);
			uRepo.saveAndFlush(deleteUser.get());
			deleted = true;
		}
		
		return deleted;
	}

	@Override
	public User findUserByUsername(String username) {
	
		return uRepo.findByUsername(username);
	}

}
