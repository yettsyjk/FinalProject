package com.skilldistillery.snitchapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Snitch;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.SnitchRepository;
import com.skilldistillery.snitchapp.repositories.UserRepository;

@Service
public class SnitchServiceImpl implements SnitchService {

	@Autowired
	private SnitchRepository sRepo;
	
	@Autowired
	private UserRepository uRepo;

	@Override
	public List<Snitch> index(String username) { 
		//for user to get all of their snitches
		return sRepo.findByUserUsername(username);
	}

	@Override //for all to see all
	public List<Snitch> findAll() {
		
		return sRepo.findAll();
	}
	
	
	@Override
	public Snitch show( Integer sId, String username) {

		return sRepo.findByIdAndUserUsername(sId, username);
	}

	@Override
	public Snitch create(String username, Snitch snitch) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			snitch.setUser(user);
			sRepo.saveAndFlush(snitch);
			return snitch;
		}
		return null;
	}

	@Override
	public Snitch update(String username, Integer sId, Snitch snitch) {
		Snitch managedSnitch =sRepo.findByIdAndUserUsername(sId, username);
		if(managedSnitch != null) {
			managedSnitch.setTitle(snitch.getTitle());
			managedSnitch.setDescription(snitch.getDescription());
			managedSnitch.setImgUrl(snitch.getImgUrl());
			managedSnitch.setResolved(snitch.getResolved());
			managedSnitch.setResolutionDate(snitch.getResolutionDate());
			managedSnitch.setResolution(snitch.getResolution());
			sRepo.saveAndFlush(managedSnitch);
			return managedSnitch;
			
		}
		return null;
	}

	@Override
	public Boolean disable(Integer sId) {
		
		Optional<Snitch> disabledSnitch = sRepo.findById(sId);
	
		if(disabledSnitch.isPresent()) {
			disabledSnitch.get().setEnabled(false);
			sRepo.saveAndFlush(disabledSnitch.get());
		
			return true;
		} else {
			return false;
		
		}
	}

	
	
	
	
	
	
		
}
