package com.skilldistillery.rollthedice.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.GameEvent;
import com.skilldistillery.rollthedice.entities.Review;
import com.skilldistillery.rollthedice.services.ReviewService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class ReviewController {
	
	@Autowired
	private ReviewService reviewSvc;
	
	@GetMapping("reviews")
	public List<Review> index(HttpServletResponse res) {
		List<Review> reviews = reviewSvc.findAllReviews();
		if (reviews.size() == 0) {
			res.setStatus(404);
		}
		return reviews;
	}
	
	@GetMapping("reviews/{id}")
	public Review findReviewById(@PathVariable int id, HttpServletResponse res) {
		Review review = reviewSvc.findReviewById(id);
		if (review == null) {
			res.setStatus(404);
			return null;
		}
		return review;
	}
	
	@PostMapping("reviews")
	public Review createReview(@RequestBody Review review, @RequestBody GameEvent gameEvent ,HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Review newReview = null;
		try {
			newReview = reviewSvc.createReview(newReview, principal.getName(), gameEvent);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newReview.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID REVIEW SENT");
			res.setStatus(400);
		}
		return newReview;
	}
	
	@PutMapping("reviews/{id}")
	public Review updateReview(@RequestBody Review review, @PathVariable int id, HttpServletResponse res, Principal principal) {
		Review updateReview = null;
		try {
			updateReview = reviewSvc.updateReview(updateReview, id, principal.getName());
			if (updateReview == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("INVALID REVIEW SENT");
		}
		return updateReview;
	}

}
