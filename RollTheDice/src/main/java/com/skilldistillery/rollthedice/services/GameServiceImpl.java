package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.Game;
import com.skilldistillery.rollthedice.entities.Genre;
import com.skilldistillery.rollthedice.entities.Game;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.GameRepository;
import com.skilldistillery.rollthedice.repositories.GenreRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GenreRepository genreRepo;

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
	public Game create(Game game, String username, List<Genre> genres) {
		User user = userRepo.findByUsername(username);
		user.getGames().add(game);
		game.getUsers().add(user);
		game.setGameOwner(user);
//		game.setGenres(genres);
		
		System.out.println("**********************************************************************" +genres);
		for (Genre genre : genres) {
			Optional<Genre> genreOp = genreRepo.findById(genre.getId());
			if(genreOp.isPresent()) {
				genre = genreOp.get();
//				game.getGenres().add(genre);
//				game.addGenre(genre);
			}
		}
		Game temp = gameRepo.saveAndFlush(game);
		return temp;
		
		
	}
	
	@Override
	public Game update(Game game, int gid, String username) {
		User user = userRepo.findByUsername(username);
		
		Optional<Game> opt = gameRepo.findById(gid);
		Game managed = null;
		if(opt.isPresent() && (user.getRole().equals("ROLE_ADMIN")) || user.getUsername().equals(game.getGameOwner().getUsername())){
			managed = opt.get();
			managed.setId(gid);
			managed.setDescription(game.getDescription());
			managed.setGameEvents(game.getGameEvents());
			managed.setGameOwner(user);
			managed.setGenres(game.getGenres());
			managed.setImageUrl(game.getImageUrl());
			managed.setLinkToGame(game.getLinkToGame());
			managed.setMaxPlayers(game.getMaxPlayers());
			managed.setName(game.getName());
			managed.setTimeToPlay(game.getTimeToPlay());
			managed.setUsers(game.getUsers());
			gameRepo.saveAndFlush(managed);
		}
		return managed;
		
	}

	
	//HAVING PROBLEMS, GENRE LIST IS GETTING LOST
//	@Override
//	public Game update(Game game, int gid, String username) {
//		Optional<Game> opt = gameRepo.findById(gid);
//		User user = userRepo.findByUsername(username);
//		
//		//Comeback for game.userid to verify 
//		if(opt.isPresent() && (user.getRole().equals("ROLE_ADMIN")) || user.getUsername().equals(game.getGameOwner().getUsername())) {  
//			game.setId(gid);
//			game.setGameOwner(user);
//			return gameRepo.saveAndFlush(game);
//		}
//		return null;
//	}

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

	@Override
	public List<Game> searchKeyword(String keyword) {
		return gameRepo.findByNameContainsOrDescriptionContains(keyword, keyword);
	}

}
