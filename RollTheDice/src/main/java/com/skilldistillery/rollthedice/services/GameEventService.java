package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.GameEvent;

public interface GameEventService {
	
	List<GameEvent> getAllGameEvents();
	
	GameEvent getGameEventById(Integer gameEventId);
	
	GameEvent addNewGameEvent(GameEvent gameEvent);
	
	boolean deleteGameEvent(Integer gameEventId);
	
	GameEvent updateGameEvent(GameEvent gameEvent);

}
