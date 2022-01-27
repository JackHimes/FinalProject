package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.Game;
import com.skilldistillery.rollthedice.entities.Genre;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.GameRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Game> index() {
		return gameRepo.findAll();
	}

	@Override
	public Game show(int id) {
		Optional<Game> op = gameRepo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Game create(Game game, String username) {
		User user = userRepo.findByUsername(username);
		if(game != null && (user.getRole().equals("ROLE_ADMIN")) || user.getUsername().equals(username)) {
			return gameRepo.saveAndFlush(game);
		}
		return null;
		
		
	}

	@Override
	public Game update(Game game, int gid, String username) {
		Optional<Game> opt = gameRepo.findById(gid);
		User user = userRepo.findByUsername(username);
		
		//Comeback for game.userid to verify the correct user is making updates
		if(opt.isPresent() && (user.getRole().equals("ROLE_ADMIN"))) {  
			game.setId(gid);
			return gameRepo.saveAndFlush(game);
		}
		return null;
	}

	@Override
	public boolean delete(int id, String username) {
		User user = userRepo.findByUsername(username);

		Optional<Game> op = gameRepo.findById(id);
		boolean deleted = false;
		
		if(op.isPresent() && user.getRole().equals("ROLE_ADMIN")) {
			gameRepo.deleteById(id);
			deleted = true;
		}

		return deleted;
	}

}
