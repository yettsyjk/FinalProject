package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.Snitch;

public interface SnitchService {
	
	List<Snitch> findByTitleLikeOrDescriptionLikeOrPinNameLikeOrStreetLike(String kw1, String kw2, String kw3, String kw4);
	List<Snitch> findByCategory_NameLike(String keyword);
    List<Snitch> index(String username);
    List<Snitch> findAll();
	Snitch show(Integer sId, String username);
	Snitch create(String username, Snitch snitch);
	Snitch update(String username, Integer sId, Snitch snitch);
	Boolean disable(Integer sId);
	
	
}
