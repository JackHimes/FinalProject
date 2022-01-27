package com.skilldistillery.rollthedice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
