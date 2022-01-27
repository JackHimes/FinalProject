package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Genre;

public interface GenreService {
	
	List<Genre> index();
	Genre show(int id);
	Genre create(Genre genre, String username);
	Genre update(Genre genre, int gid, String username);
	boolean delete(int id, String username);

}
