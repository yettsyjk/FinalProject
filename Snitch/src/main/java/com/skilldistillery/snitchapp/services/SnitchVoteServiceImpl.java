package com.skilldistillery.snitchapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.SnitchVote;
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
	public SnitchVote flipVote(Integer snitchId, Integer svId) {
		// DB has a default value of 1 for true
		SnitchVote existingVote = svoteRepo.findByIdAndSnitchId(svId, snitchId);
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
