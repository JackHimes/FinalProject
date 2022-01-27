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

import com.skilldistillery.rollthedice.entities.Genre;
import com.skilldistillery.rollthedice.services.GenreService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class GenreController {
	
	@Autowired
	private GenreService genreServ;

			  ////////////////////////////
			 /////// GET Genres /////////
			////////////////////////////
	@GetMapping("genres")
	public List<Genre> index(HttpServletRequest req, HttpServletResponse res) {
		List<Genre> genres = genreServ.index();
		if (genres != null) {
			res.setStatus(200);
			return genres;
		} else {
			res.setStatus(404);
			genres = null;
		}
		return genres;
	}
	
	  /////////////////////////////
	 ///// GET genres/{gid} //////
	/////////////////////////////
	@GetMapping("genres/{gid}")
	public Genre show(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid) {
		Genre genre = genreServ.show(gid);
		if(genre != null) {
			res.setStatus(200);
			return genre;
		}
		else {
			res.setStatus(404);
			genre = null;
		}
		return genre;
	}
	
	  /////////////////////////
	 /////// POST genre //////
	/////////////////////////
	@PostMapping("genres")
	public Genre create(HttpServletRequest req, HttpServletResponse res, @RequestBody Genre genre, Principal principal) {
		try {
			genreServ.create(genre, principal.getName());
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(genre.getId());
			res.setHeader("Location", url.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON FOR NEW Genre");
			res.setStatus(400);
			genre = null;
		}
		
		return genre;
	}
	

	///////////////////////////
	///// PUT genres/gid} ////
	/////////////////////////
	
	@PutMapping("genres/{gid}")
	public Genre update(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid, @RequestBody Genre genre, Principal principal) {
		try {
			genre = genreServ.update(genre, gid, principal.getName());
			if(genre == null) {
				res.setStatus(404);
			}else {
				res.setStatus(200);
			}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			genre = null;
		}
		
		return genre;
	}
	
	///////////////////////////
	///  DELETE genres/{gid} //
	/////////////////////////
	@DeleteMapping("genres/{gid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int gid, Principal principal) {
		try {
			if (genreServ.delete(gid, principal.getName())) {
				res.setStatus(204);
			} else {
				res.setStatus(400);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	

}
