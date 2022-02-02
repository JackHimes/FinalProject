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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.Game;
import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("users")
	public List<User> findAllUsers(HttpServletRequest req, HttpServletResponse res) {
		return userService.findAllUsers();
	}

	@GetMapping("users/{userId}")
	public User findUserById(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId) {
		User resultUser = userService.findUserById(userId);
		if (resultUser == null) {
			res.setStatus(404);
		}
		return resultUser;
	}


	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int userId, 
			@RequestBody User user) {
		User updatedUser = userService.updateUser(principal.getName(), user, userId);
		if (updatedUser != null) {
			res.setStatus(201);
		} else {
			res.setStatus(400);
			System.err.println("Invalid user, user not updated");
		}
		return updatedUser;
	}

	@DeleteMapping("users/{userId}")
	public void destroyUser(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable int userId) {

		boolean deleted = userService.destroyUser(principal.getName(), userId);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
			System.err.println("Error deleting user.");
		}
	}

	
	@PutMapping("users/{userId}/users/{friendId}")
	public User addFriend(@PathVariable int userId, @PathVariable int friendId, HttpServletResponse res, Principal principal) {
		User user = new User();
		try {
			user = userService.addFriend(userId, friendId);
			if (user != null) {
				res.setStatus(200);
			} else res.setStatus(404);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("Error adding friend");
		}
		return user;
	}
	

	@PutMapping("users/{userId}/gameevents/{gId}")
	public GameEvent addGuestToGameEvent(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@PathVariable int gId, Principal principal) {
		GameEvent gameEvent = null;
		User user = userService.findUserById(userId);
		try {
			if (user.getUsername().equals(principal.getName())) {
				gameEvent = userService.addGuestToGameEvent(principal.getName(), gId);
				res.setStatus(201);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("Error adding guest to Game Event");
		}
		return gameEvent;
	}

	@PutMapping("users/games/{gameId}")
	public Game addGameToUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int gameId,
			Principal principal) {
		Game game = null;
		try {

			game = userService.addGameToUser(principal.getName(), gameId);
			res.setStatus(201);

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("Error adding game to user");
		}
		return game;
	}

	@GetMapping("users/search/{keyword}")
	public List<User> showByKeyword(@PathVariable String keyword, HttpServletResponse res) {
		List<User> resultUsers = null;
		try {
			resultUsers = userService.searchKeyword(keyword);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return resultUsers;
	}

}
