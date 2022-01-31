package com.skilldistillery.rollthedice.controllers;

import java.security.Principal;
import java.util.ArrayList;
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

import com.skilldistillery.rollthedice.entities.Game;
import com.skilldistillery.rollthedice.entities.Genre;
import com.skilldistillery.rollthedice.services.GameService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class GameController {

	@Autowired
	private GameService gameServ;

	////////////////////////////
	/////// GET Games /////////
	////////////////////////////
	@GetMapping("games")
	public List<Game> index(HttpServletRequest req, HttpServletResponse res) {
		List<Game> game = gameServ.index();
		if (game != null) {
			res.setStatus(200);
			return game;
		} else {
			res.setStatus(404);
			game = null;
		}
		return game;
	}

/////////////////////////////
///// GET Games/{gid} //////
/////////////////////////////
	@GetMapping("games/{gid}")
	public Game show(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid) {
		Game game = gameServ.show(gid);
		if (game != null) {
			res.setStatus(200);
			return game;
		} else {
			res.setStatus(404);
			game = null;
		}
		return game;
	}

/////////////////////////
/////// POST Games //////
/////////////////////////
	@PostMapping("games")
	public Game create(HttpServletRequest req, HttpServletResponse res, @RequestBody Game game,
			Principal principal) {
		try {
			List<Genre> temp = new ArrayList<>(game.getGenres());
			
			gameServ.create(game, principal.getName(), temp);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(game.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON FOR NEW Game");
			res.setStatus(400);
			game = null;
		}

		return game;
	}

///////////////////////////
///// PUT Games/gid} ////
/////////////////////////

	@PutMapping("games/{gid}")
	public Game update(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid,
			@RequestBody Game game, Principal principal) {
		try {
			game = gameServ.update(game, gid, principal.getName());
			if (game == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			game = null;
		}

		return game;
	}

///////////////////////////
///  DELETE Games/{gid} //
/////////////////////////
	@DeleteMapping("games/{gid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid, Principal principal) {
		try {
			if (gameServ.delete(gid, principal.getName())) {
				res.setStatus(204);
			} else {
				res.setStatus(400);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@GetMapping("games/search/{keyword}")
	public List<Game> showByKeyword(@PathVariable String keyword, HttpServletResponse res) {
		List<Game> resultGames = null;
		try {
			resultGames = gameServ.searchKeyword(keyword);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return resultGames;
	}

}
