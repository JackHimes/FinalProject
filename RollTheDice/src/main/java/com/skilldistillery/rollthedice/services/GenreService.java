package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Genre;

public interface GenreService {
	
	List<Genre> index();
	Genre show(int id);
	Genre create(Genre genre);
	Genre update(Genre genre, int gid);
	boolean delete(int id);

}
