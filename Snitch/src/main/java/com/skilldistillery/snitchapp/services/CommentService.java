package com.skilldistillery.snitchapp.services;

import java.util.List;

import com.skilldistillery.snitchapp.entities.Comment;

public interface CommentService {

	List<Comment> findAll();
	
	List<Comment> findForSnitchId(Integer sId);
	
	List<Comment> findByUsername(String username);
	
	Comment createComment(String username, Comment comment, Integer sID);
	
	public Boolean deleteComment(String username, Integer commentId, Integer sId);
	
	
}
