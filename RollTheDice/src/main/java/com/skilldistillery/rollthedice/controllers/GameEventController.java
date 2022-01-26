package com.skilldistillery.rollthedice.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.services.GameEventService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class GameEventController {

	@Autowired
	private GameEventService gameEventService;
	
	@GetMapping("gameevents")
	public List<GameEvent> showAllGameEvents() {
		return gameEventService.getAllGameEvents();
	}
	
	@GetMapping("gameevents/{gameeventId}")
	public GameEvent findById(@PathVariable Integer gameEventId) {
		GameEvent gameEvent = gameEventService.getGameEventById(gameEventId);
		return gameEvent;
	}
	
	@PostMapping("gameevents")
	public GameEvent createGameEvent(@RequestBody GameEvent gameEvent, HttpServletRequest req, HttpServletResponse res) {
		try {
			gameEvent = gameEventService.addNewGameEvent(gameEvent);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(gameEvent.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			gameEvent = null;
		}
		return gameEvent;
	}
	
}
