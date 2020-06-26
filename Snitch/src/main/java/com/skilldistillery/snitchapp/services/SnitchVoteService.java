package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.SnitchVote;

public interface SnitchVoteService {

	
	List <SnitchVote> findAll();
	
	List<SnitchVote> findBySnitchId(Integer sId);
	
	SnitchVote flipVote (Integer snitchId, Integer svId);
	
}

