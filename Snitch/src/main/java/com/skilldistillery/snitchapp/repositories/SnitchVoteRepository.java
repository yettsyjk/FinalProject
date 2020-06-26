package com.skilldistillery.snitchapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.SnitchVote;
import com.skilldistillery.snitchapp.entities.SnitchVoteId;

public interface SnitchVoteRepository extends JpaRepository<SnitchVote, Integer> {
	
	List<SnitchVote> findBySnitchId(Integer sId);

	SnitchVote findByIdAndSnitchId(SnitchVoteId svoteId, Integer sId);
	
	SnitchVote findById(SnitchVoteId svId);
	
}
