package com.skilldistillery.snitchapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByUserUsername(String username);
	
	List<Comment> findBySnitch_Id(Integer sId);
}
