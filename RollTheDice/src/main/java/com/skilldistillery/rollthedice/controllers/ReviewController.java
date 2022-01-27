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
	
	@PostMapping("gameevents/{gId}/reviews")
	public Review createReview(@RequestBody Review review, @PathVariable int gId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Review newReview = null;
		try {
			System.out.println(review.getGameEvent());
			newReview = reviewSvc.createReview(review, principal.getName(), gId);
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
	
	@PutMapping("gameevents/{gId}/reviews/{id}")
	public Review updateReview(@RequestBody Review review, @PathVariable int id, @PathVariable int gId, HttpServletResponse res, Principal principal) {
		Review updateReview = null;
		try {
			updateReview = reviewSvc.updateReview(review, id, principal.getName(), gId);
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
	
	@DeleteMapping("gameevents/{gId}/reviews/{id}")
	public void deleteReview(@PathVariable int id, @PathVariable int gId, HttpServletResponse res, Principal principal) {
		try {
			boolean isDeleted = reviewSvc.deleteReview(id, principal.getName(), gId);
			if (isDeleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

}
