package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.Review;

public interface ReviewService {

	List<Review> findAllReviews();

	Review findReviewById(int id);
	
	Review createReview(Review review, String username, int gId);

	Review updateReview(Review review, int id, String username, int gId);

	boolean deleteReview(int id, String username, int gId);

}
