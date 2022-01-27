package com.skilldistillery.rollthedice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	List<Comment> findByGameEvent_Id(Integer eventId);

}
