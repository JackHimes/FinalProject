package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Comment;

public interface CommentService {
	
	public List<Comment> findAllComments(String username);
	
	public Comment findCommentById(String username, int commentId);
	
	public Comment createComment(String username, Comment comment);
	
	public Comment updateComment(String username, Comment comment, int commentId);
	
	public boolean destroyComment(String username, int commentId);

}
