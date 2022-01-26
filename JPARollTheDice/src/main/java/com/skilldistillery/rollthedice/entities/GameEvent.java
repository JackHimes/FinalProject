package com.skilldistillery.rollthedice.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToMany(mappedBy="gameEvent")
	private List<Comment> comments;
	
	@OneToMany(mappedBy="gameEvent")
	private List<Review> reviews;
	
	@ManyToMany(mappedBy="gameEvents")
	private List<Game> games;
	
	@ManyToMany(mappedBy="gameEvents")
	private List<User> guests;
	
	@ManyToMany(mappedBy = "gameEvents")
	private List<EventTag> eventTags;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(dateOfEvent, description, enabled, endTime, id, imageUrl, maxNumberOfGuests, startTime,
				title);
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
		return Objects.equals(dateOfEvent, other.dateOfEvent) && Objects.equals(description, other.description)
				&& enabled == other.enabled && Objects.equals(endTime, other.endTime) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && maxNumberOfGuests == other.maxNumberOfGuests
				&& Objects.equals(startTime, other.startTime) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "GameEvent [id=" + id + ", dateOfEvent=" + dateOfEvent + ", maxNumberOfGuests=" + maxNumberOfGuests
				+ ", enabled=" + enabled + ", startTime=" + startTime + ", endTime=" + endTime + ", imageUrl="
				+ imageUrl + ", description=" + description + ", title=" + title + "]";
	}

}
