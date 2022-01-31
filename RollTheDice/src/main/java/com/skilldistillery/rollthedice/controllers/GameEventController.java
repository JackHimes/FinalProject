package com.skilldistillery.rollthedice.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.services.GameEventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class GameEventController {

	@Autowired
	private GameEventService gameEventService;

	@GetMapping("gameevents")
	public List<GameEvent> showAllGameEvents() {
		return gameEventService.getAllGameEvents();
	}

	@GetMapping("gameevents/{gameEventId}")
	public GameEvent findById(@PathVariable Integer gameEventId) {
		GameEvent gameEvent = gameEventService.getGameEventById(gameEventId);
		return gameEvent;
	}

	@PostMapping("gameevents")
	public GameEvent createGameEvent(@RequestBody GameEvent gameEvent, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		try {
			gameEvent = gameEventService.addNewGameEvent(principal.getName(), gameEvent);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(gameEvent.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			System.err.println("Invalid JSON or unauthorized user");
			gameEvent = null;
		}
		return gameEvent;
	}
	
	@PutMapping("gameevents/{gId}/users/{uId}")
	public GameEvent joinGameEvent(@PathVariable int gId, @PathVariable int uId, Principal principal, HttpServletResponse res) {
		GameEvent updatedGameEvent = null;
		try {
			updatedGameEvent = gameEventService.joinGameEvent(gId, uId, principal.getName());
			if (updatedGameEvent == null) {
				res.setStatus(404);
			} else res.setStatus(200);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedGameEvent;
	}
	
	@PutMapping("gameevents/users/{gId}/{uId}")
	public GameEvent leaveGameEvent(@PathVariable int gId, @PathVariable int uId, Principal principal, HttpServletResponse res) {
		GameEvent updatedGameEvent = null;
		try {
			updatedGameEvent = gameEventService.leaveGameEvent(gId, uId, principal.getName());
			if (updatedGameEvent == null) {
				res.setStatus(404);
			} else res.setStatus(200);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedGameEvent;
	}

	@PutMapping("gameevents/{gameEventId}")
	public GameEvent updateGameEvent(@PathVariable Integer gameEventId, @RequestBody GameEvent gameEvent,
			Principal principal, HttpServletResponse res) {
		GameEvent updatedGameEvent = null;
		try {
			updatedGameEvent = gameEventService.updateGameEvent(principal.getName(), gameEvent, gameEventId);

			if (updatedGameEvent == null) {
				res.setStatus(404);
			}
			else res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(400);
			updatedGameEvent = null;
		}
		return updatedGameEvent;

	}
	
	@DeleteMapping("gameevents/{gameEventId}")
	public void deleteGameEvent(@PathVariable Integer gameEventId, Principal principal, HttpServletResponse res) {
		try {
			boolean isDeleted = gameEventService.deleteGameEvent(principal.getName(), gameEventId);
			if (isDeleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
	
	@GetMapping("gameevents/search/{keyword}")
	public List<GameEvent> showByKeyword(@PathVariable String keyword, HttpServletResponse res) {
		List<GameEvent> resultGameEvents = null;
		try {
			resultGameEvents = gameEventService.searchKeyword(keyword);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return resultGameEvents;
	}
	
	

}
