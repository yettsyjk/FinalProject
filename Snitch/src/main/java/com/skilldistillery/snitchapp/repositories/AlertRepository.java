package com.skilldistillery.snitchapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
	
	
	

}
