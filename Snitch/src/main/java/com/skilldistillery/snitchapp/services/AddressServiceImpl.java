package com.skilldistillery.snitchapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Address;
import com.skilldistillery.snitchapp.entities.Snitch;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.AddressRepository;
import com.skilldistillery.snitchapp.repositories.SnitchRepository;
import com.skilldistillery.snitchapp.repositories.UserRepository;
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
    private AddressRepository aRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private SnitchRepository sRepo;
	
	@Override
	public Address create(String username, Address adr, Integer sId) {
		User user = uRepo.findByUsername(username);
		Snitch snitch = sRepo.findById(sId).get();//snitch is not in the DB yet(being created the moment the adr

		if (user != null && snitch != null) {
			aRepo.saveAndFlush(adr);
		}
		return adr;
	}

	
	
}
