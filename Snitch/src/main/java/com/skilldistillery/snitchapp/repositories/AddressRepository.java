package com.skilldistillery.snitchapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
