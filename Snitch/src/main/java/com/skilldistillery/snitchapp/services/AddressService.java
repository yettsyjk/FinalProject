package com.skilldistillery.snitchapp.services;

import com.skilldistillery.snitchapp.entities.Address;

public interface AddressService {

	Address create(String username, Address adr, Integer sId);
	
	Address create(Address adr);
	
	
}
