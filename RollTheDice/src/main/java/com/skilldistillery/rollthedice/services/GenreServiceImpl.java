package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.Genre;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.GenreRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private GenreRepository genreRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Genre> index() {
		
		return genreRepo.findAll();
	}

	@Override
	public Genre show(int id) {

		Optional<Genre> op = genreRepo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}
	
	@Override
	public Genre create(Genre genre, String username) {
		User user = userRepo.findByUsername(username);
		if(genre != null && user.getRole().equals("ROLE_ADMIN")) {
			return genreRepo.saveAndFlush(genre);
		}
		return null;
		
		
	}
	

	@Override
	public Genre update(Genre genre, int gid, String username) {
		User user = userRepo.findByUsername(username);
		
		Optional<Genre> opt = genreRepo.findById(gid);
		Genre managed = null;
		if(opt.isPresent() && user.getRole().equals("ROLE_ADMIN")) {
			managed = opt.get();
			managed.setDescription(genre.getDescription());
			managed.setGames(genre.getGames());
			managed.setName(genre.getName());
			genreRepo.saveAndFlush(managed);
		}
		return managed;
		
	}
	


	@Override
	public boolean delete(int id, String username) {
		User user = userRepo.findByUsername(username);

		Optional<Genre> op = genreRepo.findById(id);
		boolean deleted = false;
		
		if(op.isPresent() && user.getRole().equals("ROLE_ADMIN")) {
			genreRepo.deleteById(id);
			deleted = true;
		}

		return deleted;
	}
	

}
