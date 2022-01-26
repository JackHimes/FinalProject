package com.skilldistillery.rollthedice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
