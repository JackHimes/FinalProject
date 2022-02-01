package com.skilldistillery.rollthedice.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "game_event")
public class GameEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_of_event")
	private LocalDate dateOfEvent;

	@Column(name = "max_number_of_guests")
	private int maxNumberOfGuests;

	private boolean enabled;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;

	@Column(name = "image_url")
	private String imageUrl;

	private String description;

	private String title;
	
//	@JsonIgnore
	@OneToMany(mappedBy="gameEvent")
	private List<Comment> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy="gameEvent")
	private List<Review> reviews;
	
//	@JsonIgnore
	@ManyToMany(mappedBy="gameEvents")
	private List<Game> games;
	
//	@JsonIgnore
	@ManyToMany(mappedBy="gameEvents")
	private List<User> guests;
	
//	@JsonIgnore
	@ManyToMany(mappedBy = "gameEvents")
	private List<EventTag> eventTags;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@JsonIgnoreProperties({"gameEvents"})
	@OneToOne
	@JoinColumn(name="host_id")
	private User host;
	


	public GameEvent() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(LocalDate dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public int getMaxNumberOfGuests() {
		return maxNumberOfGuests;
	}

	public void setMaxNumberOfGuests(int maxNumberOfGuests) {
		this.maxNumberOfGuests = maxNumberOfGuests;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<User> getGuests() {
		return guests;
	}

	public void setGuests(List<User> guests) {
		this.guests = guests;
	}

	public List<EventTag> getEventTags() {
		return eventTags;
	}

	public void setEventTags(List<EventTag> eventTags) {
		this.eventTags = eventTags;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}
	
	public void addReview(Review review) {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		if (!reviews.contains(review)) {
			reviews.add(review);
			review.setGameEvent(this);
		}
	}
	
	public void addComment(Comment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			comment.setGameEvent(this);
		}
	}
	
	public void addGame(Game game) {
		if (games == null) {
			games = new ArrayList<>();
		}
		if (!games.contains(game)) {
			games.add(game);
			game.addGameEvent(this);
		}
	}
	
	public void addGuest(User guest) {
		if (guests == null) {
			guests = new ArrayList<>();
		}
		if(!guests.contains(guest)) {
			guests.add(guest);
			guest.addGameEvent(this);
		}
	}
	
	public void addEventTag(EventTag eventTag) {
		if (eventTags == null) {
			eventTags = new ArrayList<>();
		}
		if (!eventTags.contains(eventTag)) {
			eventTags.add(eventTag);
			eventTag.addGameEvent(this);
		}
	}
	
	public void removeComment(Comment comment) {
		if (comments != null && comments.contains(comment)) {
			comments.remove(comment);
		}
	}
	
	public void removeGame(Game game) {
		if (games != null && games.contains(game)) {
			games.remove(game);
			game.removeGameEvent(this);
		}
	}
	
	public void removeGuest(User guest) {
		if (guests != null && guests.contains(guest)) {
			guests.remove(guest);
			guest.removeGameEvent(this);
		}
	}
	
	public void removeEventTag(EventTag eventTag) {
		if (eventTags != null && eventTags.contains(eventTag)) {
			eventTags.remove(eventTag);
			eventTag.removeGameEvent(this);
		}
	}
	
	public void removeReview(Review review) {
		if (reviews != null && reviews.contains(review)) {
			reviews.remove(review);
		}
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(address, comments, dateOfEvent, description, enabled, endTime, eventTags, games, guests,
				host, id, imageUrl, maxNumberOfGuests, reviews, startTime, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameEvent other = (GameEvent) obj;
		return Objects.equals(address, other.address) && Objects.equals(comments, other.comments)
				&& Objects.equals(dateOfEvent, other.dateOfEvent) && Objects.equals(description, other.description)
				&& enabled == other.enabled && Objects.equals(endTime, other.endTime)
				&& Objects.equals(eventTags, other.eventTags) && Objects.equals(games, other.games)
				&& Objects.equals(guests, other.guests) && Objects.equals(host, other.host) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && maxNumberOfGuests == other.maxNumberOfGuests
				&& Objects.equals(reviews, other.reviews) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "GameEvent [id=" + id + ", dateOfEvent=" + dateOfEvent + ", maxNumberOfGuests=" + maxNumberOfGuests
				+ ", enabled=" + enabled + ", startTime=" + startTime + ", endTime=" + endTime + ", imageUrl="
				+ imageUrl + ", description=" + description + ", title=" + title + ", comments=" + comments
				+ ", reviews=" + reviews + ", games=" + games + ", guests=" + guests + ", eventTags=" + eventTags
				+ ", address=" + address + ", host=" + host + "]";
	}

}
