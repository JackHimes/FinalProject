package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.Comment;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.CommentRepository;
import com.skilldistillery.rollthedice.repositories.GameEventRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private GameEventRepository gameEventRepo;

	@Override
	public List<Comment> findAllComments(String username) {
		return commentRepo.findAll();
	}

	@Override
	public Comment findCommentById(String username, int commentId) {
		Optional<Comment> commentOp = commentRepo.findById(commentId);
		if (commentOp.isPresent()) {
			return commentOp.get();
		}
		return null;
	}

	@Override
	public Comment createComment(String username, Comment comment, int gId) {
		comment.setGameEvent(gameEventRepo.getById(gId));
		User loggedInUser = userRepo.findByUsername(username);
		comment.setUser(loggedInUser);
		return commentRepo.saveAndFlush(comment);
	}

	@Override
	public Comment updateComment(String username, Comment comment, int commentId, int gId) {
		comment.setId(commentId);
		User loggedInUser = userRepo.findByUsername(username);
		comment.setUser(loggedInUser);
		Optional<Comment> updatedComment = commentRepo.findById(commentId);
		if (updatedComment.isPresent()
				&& ((loggedInUser.getId() == comment.getUser().getId()) || loggedInUser.getRole().equals("ROLE_ADMIN"))
				&& gId == comment.getGameEvent().getId()) {
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}

	@Override
	public boolean destroyComment(String username, int commentId, int gId) {
		boolean deleted = false;
		Optional<Comment> commentOp = commentRepo.findById(commentId);
		Comment comment = commentOp.get();
		User loggedInUser = userRepo.findByUsername(username);
		if (commentOp.isPresent()
				&& ((loggedInUser.getId() == comment.getUser().getId()) || loggedInUser.getRole().equals("ROLE_ADMIN"))
				&& comment.getGameEvent().getId() == gId) {
			commentRepo.delete(comment);
			deleted = true;
		}
		return deleted;
	}

}
