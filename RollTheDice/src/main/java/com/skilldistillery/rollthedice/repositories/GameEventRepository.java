package com.skilldistillery.rollthedice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.GameEvent;

public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {
	
	GameEvent queryById(int id);
	
	List<GameEvent> findByTitleContainsOrDescriptionContains(String title, String description);

}
