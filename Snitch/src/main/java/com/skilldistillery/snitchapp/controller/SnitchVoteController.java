package com.skilldistillery.snitchapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.SnitchVote;
import com.skilldistillery.snitchapp.services.SnitchService;
import com.skilldistillery.snitchapp.services.SnitchVoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class SnitchVoteController {

	@Autowired
	SnitchVoteService svoteSvc;
	
	@Autowired
	SnitchService snitchSvc;
	
	@PostMapping("snitches/{userId}/vote/{snitchId}")
	public SnitchVote flipSnitchVote(
			@PathVariable Integer userId, 
			@PathVariable Integer snitchId,
			@RequestBody SnitchVote snitchvote,
			HttpServletResponse resp) {
				
		System.out.println("userID: " + userId);
		System.out.println("SnitchId: " +snitchId);
		SnitchVote flippedSnitchVote = svoteSvc.flipVote(userId, snitchId);
		
			if (flippedSnitchVote == null) {
				resp.setStatus(404);
				
			}else {
				resp.setStatus(200);
			}
		return flippedSnitchVote; 
		
	}
	
	
	
}

