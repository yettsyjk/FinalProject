package com.skilldistillery.snitchapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.snitchapp.entities.Comment;
import com.skilldistillery.snitchapp.services.CommentService;
import com.skilldistillery.snitchapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class CommentController {

	@Autowired
	private CommentService cSvc;

	@Autowired
	private UserService uSvc;

	// this retrieves ALL COMMENTS by ALL USERS!!! only real use is for ADMIN
	@GetMapping("comments")
	public List<Comment> index() {
		return cSvc.findAll();

	}

	// retrieves all comments for logged in USER
	@GetMapping("snitches/{sId}/comments")
	public List<Comment> commentsForPost(@PathVariable Integer sId, HttpServletResponse response) {

		List<Comment> comments = cSvc.findForSnitchId(sId);
		if (comments == null) {
			response.setStatus(404);
		}
		return comments;
	}

	@PostMapping("snitches/{sId}/comments")
	public Comment create(@PathVariable Integer sId, @RequestBody Comment comment, Principal principal,
			HttpServletResponse resp, HttpServletRequest req) {

		try {
			comment = cSvc.createComment(principal.getName(), comment, sId);

			if (comment == null) {
				resp.setStatus(404);
			} else {
				resp.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(comment.getId());
				resp.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(409);
		}
		return comment;
	}

	@DeleteMapping("snitches/{sId}/comments/{commentId}")
	public void deleteComment(@PathVariable Integer sId, @PathVariable Integer commentId, HttpServletResponse response,
			Principal principal) {
		try {
			if (cSvc.deleteComment(principal.getName(), commentId, sId)) {
				response.setStatus(204);
			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(409);
		}
	}
}
