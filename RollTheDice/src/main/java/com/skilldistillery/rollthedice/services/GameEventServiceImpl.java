package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

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
		return gameEventRepo.findAll();
	}

	@Override
	public GameEvent getGameEventById(Integer gameEventId) {
//		Optional<GameEvent> gameEventOptional = gameEventRepo.findById(gameEventId);
//		if (gameEventOptional.isPresent()) {
//			GameEvent resultGameEvent = gameEventOptional.get();
//			if (resultGameEvent.get)
//		}
		return gameEventRepo.findById(gameEventId).get();
	}

	@Override
	public GameEvent addNewGameEvent(GameEvent gameEvent) {
		return gameEventRepo.saveAndFlush(gameEvent);
	}

	@Override
	public boolean deleteGameEvent(Integer gameEventId) {
		boolean isDeleted = false;
		if (gameEventRepo.existsById(gameEventId)) {
			gameEventRepo.deleteById(gameEventId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public GameEvent updateGameEvent(GameEvent gameEvent) {
		Optional<GameEvent> gameEventOp = gameEventRepo.findById(gameEvent.getId());
		if (gameEventOp.isPresent()) {
			GameEvent updatedGameEvent = gameEventOp.get();
			updatedGameEvent.setDateOfEvent(gameEvent.getDateOfEvent());
			updatedGameEvent.setMaxNumberOfGuests(gameEvent.getMaxNumberOfGuests());
			updatedGameEvent.setEnabled(gameEvent.isEnabled());
			updatedGameEvent.setStartTime(gameEvent.getStartTime());
			updatedGameEvent.setEndTime(gameEvent.getEndTime());
			updatedGameEvent.setImageUrl(gameEvent.getImageUrl());
			updatedGameEvent.setDescription(gameEvent.getDescription());
			updatedGameEvent.setTitle(gameEvent.getTitle());
			return gameEventRepo.saveAndFlush(updatedGameEvent);
		}
		return null;
	}

}
