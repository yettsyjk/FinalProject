package com.skilldistillery.snitchapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.SnitchVote;
import com.skilldistillery.snitchapp.entities.SnitchVoteId;
import com.skilldistillery.snitchapp.repositories.SnitchRepository;
import com.skilldistillery.snitchapp.repositories.SnitchVoteRepository;

@Service
public class SnitchVoteServiceImpl implements SnitchVoteService {

	@Autowired
	SnitchVoteRepository svoteRepo;

	@Autowired
	SnitchRepository sRepo;

	@Override
	public List<SnitchVote> findAll() {
		return null;
	}

	@Override
	public List<SnitchVote> findBySnitchId(Integer sId) {
		if (!sRepo.existsById(sId)) {
			return null;
		}
		List<SnitchVote> votes = svoteRepo.findBySnitchId(sId);
		return votes;
	}

	@Override
	public SnitchVote flipVote(int userId, int snitchId) {
		// DB has a default value of 1 for true
		SnitchVoteId snitchVoteId= new SnitchVoteId(userId, snitchId);
		
		//System.out.println(snitchVoteId);
		
		SnitchVote existingVote = svoteRepo.findById(snitchVoteId);
		
		//System.out.println(existingVote);
		
		if (existingVote != null) {
			if (existingVote.getVote() == true) {
				existingVote.setVote(false);
			} else {
				existingVote.setVote(true);
			}
			return svoteRepo.saveAndFlush(existingVote);
		}
		return null;
	}

}

// fliupVote does not create a vote, only changes it (F to T, T to F)
// shows in DB
// create method w/ POST mapping to create a vote
// existing method will be changed to "PUT"
// add auth. so user can only create and flip own vote
