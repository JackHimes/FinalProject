package com.skilldistillery.rollthedice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
