package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.GameEventRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class GameEventServiceImpl implements GameEventService {

	@Autowired
	private GameEventRepository gameEventRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<GameEvent> getAllGameEvents() {
		return gameEventRepo.findAll();
	}

	@Override
	public GameEvent getGameEventById(Integer gameEventId) {
		return gameEventRepo.findById(gameEventId).get();
	}

	@Override
	public GameEvent addNewGameEvent(String username, GameEvent gameEvent) {
		User user = userRepo.findByUsername(username);
		user.addHostedGameEvent(gameEvent);
		gameEvent.setHost(user);
		userRepo.saveAndFlush(user);
		return gameEventRepo.saveAndFlush(gameEvent);
	}

	@Override
	public boolean deleteGameEvent(String username, Integer gameEventId) {
		User loggedInUser = userRepo.findByUsername(username);
		boolean isDeleted = false;
		if (gameEventRepo.existsById(gameEventId)) {
			if (loggedInUser.getUsername().equals(username) || loggedInUser.getRole().equals("ROLE_ADMIN")) {
			gameEventRepo.deleteById(gameEventId);
			isDeleted = true;
			}
		}
		return isDeleted;
	}

	@Override
	public GameEvent updateGameEvent(String username, GameEvent gameEvent, Integer gameEventId) {
		Optional<GameEvent> gameEventOp = gameEventRepo.findById(gameEventId);
		User loggedInUser = userRepo.findByUsername(username);
		if (gameEventOp.isPresent()) {
			GameEvent updatedGameEvent = gameEventOp.get();
			if(updatedGameEvent.getHost().getUsername().equals(loggedInUser.getUsername()) || loggedInUser.getRole().equals("ROLE_ADMIN")) {
				gameEvent.setId(gameEventId);
				return gameEventRepo.saveAndFlush(gameEvent);
			}
		}
		return null;
	}

	@Override
	public List<GameEvent> searchKeyword(String keyword) {
		return gameEventRepo.findByTitleContainsOrDescriptionContains(keyword, keyword);
	}

}
