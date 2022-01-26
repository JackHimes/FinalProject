package com.skilldistillery.rollthedice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.repositories.GameEventRepository;

@Service
public class GameEventServiceImpl implements GameEventService {
	
	@Autowired
	private GameEventRepository gameEventRepo;

	@Override
	public List<GameEvent> getAllGameEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameEvent getGameEventById(Integer gameEventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameEvent addNewGameEvent(GameEvent gameEvent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteGameEvent(Integer gameEventId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameEvent updateGameEvent(GameEvent gameEvent) {
		// TODO Auto-generated method stub
		return null;
	}

}
