package com.skilldistillery.snitchapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.snitchapp.entities.Comment;
import com.skilldistillery.snitchapp.entities.Role;
import com.skilldistillery.snitchapp.entities.Snitch;
import com.skilldistillery.snitchapp.entities.User;
import com.skilldistillery.snitchapp.repositories.CommentRepository;
import com.skilldistillery.snitchapp.repositories.SnitchRepository;
import com.skilldistillery.snitchapp.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private SnitchRepository sRepo;

	@Autowired
	private CommentRepository cRepo;

	@Override
	public List<Comment> findAll() {

		return cRepo.findAll();
	}

	@Override
	public Comment createComment(String username, Comment comment, Integer sId) {
		Snitch snitch = sRepo.findById(sId).get();
		User user = uRepo.findByUsername(username);

		if (user != null && snitch != null) {
			comment.setSnitch(snitch);
			comment.setUser(user);
			cRepo.saveAndFlush(comment);
		}
		return comment;
	}

	@Override
	public Boolean deleteComment(String username, Integer commentId, Integer sId) {
		boolean deletedComment = false;
		User user = uRepo.findByUsername(username);
		Comment comment = cRepo.findById(commentId).get();
		if (user.getRole() == Role.ADMIN) {
			if (comment.getSnitch().getId() == sId) {
				cRepo.delete(comment);
				deletedComment = true;
			}
		}
		return deletedComment;
	}

	@Override
	public List<Comment> findByUsername(String username) {

		return cRepo.findByUserUsername(username);
	}

	@Override
	public List<Comment> findForSnitchId(Integer sId) {
		if (!sRepo.existsById(sId)) {
			return null;
		}
		return cRepo.findBySnitch_Id(sId);

	}

	// currently user cannot delete own comment...deal with it
}
