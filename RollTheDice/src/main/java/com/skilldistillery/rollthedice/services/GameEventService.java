package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.GameEvent;

public interface GameEventService {
	
	List<GameEvent> getAllGameEvents();
	
	GameEvent getGameEventById(Integer gameEventId);
	
	GameEvent addNewGameEvent(String username, GameEvent gameEvent);
	
	boolean deleteGameEvent(String username, Integer gameEventId);
	
	GameEvent updateGameEvent(String username, GameEvent gameEvent, Integer gameEventId);

}
