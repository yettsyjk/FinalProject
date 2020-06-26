package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.Alert;

public interface AlertService {
	
	
	List<Alert> findAllAlerts();

	
	
	Alert createAlert(String username, Alert alert);
	
	
	Boolean deleteAlert(int aId);
}
