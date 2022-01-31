package com.skilldistillery.rollthedice.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.Comment;
import com.skilldistillery.rollthedice.entities.Review;
import com.skilldistillery.rollthedice.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("comments")
	public List<Comment> getAllComments(String username, HttpServletResponse res, Principal principal) {
		List<Comment> comments = commentService.findAllComments(principal.getName());
		if (comments.size() == 0) {
			res.setStatus(404);
		}
		return comments;
	}
	
	@GetMapping("comments/{commentId}")
	public Comment findCommentById(@PathVariable int commentId, HttpServletResponse res, Principal principal) {
		Comment comment = commentService.findCommentById(principal.getName(), commentId);
		if (comment == null) {
			res.setStatus(404);
			return null;
		}
		return comment;
	}
	
	@PostMapping("gameevents/{gId}/comments")
	public Comment createComment(HttpServletResponse res, HttpServletRequest req, Principal principal, @PathVariable int gId, @RequestBody Comment comment) {
		Comment newComment = null;	
		try {
			newComment = commentService.createComment(principal.getName(), comment, gId);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Comment Sent");
			res.setStatus(400);
		}
		return newComment;
	}
	
	@PutMapping("gameevents/{gId}/comments/{commentId}")
	public Comment updateComment(HttpServletResponse res, HttpServletRequest req, Principal principal, @PathVariable int gId, @RequestBody Comment comment, @PathVariable int commentId) {
		Comment updateComment = null;
		try {
			updateComment = commentService.updateComment(principal.getName(), comment, commentId, gId);
			if (updateComment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("Invalid Comment Sent");
		}
		return updateComment;
	}
	
	@DeleteMapping("gameevents/{gId}/comments/{commentId}")
	public void deleteComment(HttpServletResponse res, HttpServletRequest req, Principal principal, @PathVariable int gId, @PathVariable int commentId) {
		try {
			boolean deleted = commentService.destroyComment(principal.getName(), commentId, gId);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("Error deleting comment");
		}
	}
}
