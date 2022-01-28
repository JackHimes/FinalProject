package com.skilldistillery.rollthedice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	List<Game> findByNameContainsOrDescriptionContains(String name, String description);
}
