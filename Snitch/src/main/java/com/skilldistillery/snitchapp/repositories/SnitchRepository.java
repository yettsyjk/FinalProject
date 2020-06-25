package com.skilldistillery.snitchapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.Snitch;


public interface SnitchRepository extends JpaRepository<Snitch, Integer> {

	List<Snitch> findByTitleLikeOrDescriptionLikeOrAddressStreetLikeOrAddressPinNameLike(String kw1, String kw2, String kw3, String kw4);
	Snitch findByIdAndUserUsername(Integer sId, String username);
	List<Snitch> findByCategory_NameLike(String keyword);
	List<Snitch> findByUserUsername(String username);
	
}
