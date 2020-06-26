package com.skilldistillery.snitchapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.SnitchVote;

public interface SnitchVoteRepository extends JpaRepository<SnitchVote, Integer> {
	
	List<SnitchVote> findBySnitchId(Integer sId);

	SnitchVote findByIdAndSnitchId(Integer svoteId, Integer sId);
}
