package com.skilldistillery.rollthedice.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Column(name = "message")
	private String message;

	@Column(name = "review_date")
	private LocalDate reviewDate;

//	@Column(name = "rating")
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private GameEvent gameEvent;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Review() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	

	public GameEvent getGameEvent() {
		return gameEvent;
	}

	public void setGameEvent(GameEvent gameEvent) {
		this.gameEvent = gameEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", message=" + message + ", reviewDate=" + reviewDate + ", rating=" + rating + "]";
	}
	
	

}
