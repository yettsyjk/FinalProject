package com.skilldistillery.snitchapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Alert;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.AlertRepository;
import com.skilldistillery.snitchapp.repositories.UserRepository;


@Service 
public class AlertServiceImpl implements AlertService {
	
	@Autowired
	private AlertRepository aRepo;
	
	
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public List<Alert> findAllAlerts() {
		return aRepo.findAll();
	}

	@Override
	public Alert createAlert(String username, Alert alert) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			alert.setUser(user);
			aRepo.saveAndFlush(alert);
			return alert;
		}
		return null;
	}

	@Override
	public Boolean deleteAlert(int aId) {
		boolean deletedAlert = false;
		Alert alertToDelete = aRepo.findById(aId).get();
		if(alertToDelete != null) {
			aRepo.delete(alertToDelete);
			deletedAlert = true;
		}
		return deletedAlert;
	}

}
