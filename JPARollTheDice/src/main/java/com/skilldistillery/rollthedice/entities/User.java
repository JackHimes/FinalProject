package com.skilldistillery.rollthedice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private Boolean enabled;
	
	private String role;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	@Column(name="profile_picture_url")
	private String profilePictureUrl;
	
//	@JsonIgnore
	@ManyToMany(mappedBy="users")
	private List<Address> addresses;
	
	@OneToOne
	@JoinColumn(name="address_id") 
	private Address homeAddress;
	
	@JsonIgnore
//	@JsonIgnoreProperties({"guests", "comments"})
	@ManyToMany
	@JoinTable(name="user_has_event",
		joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="event_id")
	)
	private List<GameEvent> gameEvents;
	
	@JsonIgnore
	@OneToMany(mappedBy="host")
	private List<GameEvent> hostedGameEvents;
	
	@ManyToMany(mappedBy="users")
	private List<Game> games;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Review> reviews;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="friends",
		joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="friend_id")
	)
	private List<User> friends;
	
	private String biography;
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
	
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public List<GameEvent> getGameEvents() {
		return gameEvents;
	}

	public void setGameEvents(List<GameEvent> gameEvents) {
		this.gameEvents = gameEvents;
	}

	public List<GameEvent> getHostedGameEvents() {
		return hostedGameEvents;
	}

	public void setHostedGameEvents(List<GameEvent> hostedGameEvents) {
		this.hostedGameEvents = hostedGameEvents;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
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

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void addAddress(Address address) {
		if (addresses == null) {
			addresses = new ArrayList<>();
		}
		
		if (!addresses.contains(address)) {
			addresses.add(address);
			address.addUser(this);
		}
	}
	
	public void removeAddress(Address address) {
		if (addresses != null && addresses.contains(address)) {
			addresses.remove(address);
			address.removeUser(this);
		}
	}
	
	public void addGameEvent(GameEvent gameEvent) {
		if (gameEvents == null) {
			gameEvents = new ArrayList<>();
		}
		
		if (!gameEvents.contains(gameEvent)) {
			gameEvents.add(gameEvent);
			gameEvent.addGuest(this);
		}
	}
	
	public void removeGameEvent(GameEvent gameEvent) {
		if (gameEvents != null && gameEvents.contains(gameEvent)) {
			gameEvents.remove(gameEvent);
			gameEvent.removeGuest(this);
		}
	}	
	
	public void addHostedGameEvent(GameEvent hostedGameEvent) {
		if (hostedGameEvents == null) {
			hostedGameEvents = new ArrayList<>();
		}
		if (!hostedGameEvents.contains(hostedGameEvent)) {
			hostedGameEvents.add(hostedGameEvent);
			hostedGameEvent.setHost(this);
		}
	}
	
	public void addGame(Game game) {
		if (games == null) {
			games = new ArrayList<>();
		}
		if (!games.contains(game)) {
			games.add(game);
			game.addUser(this);
			
		}
	}
	
	public void removeGame(Game game) {
		if (games != null && games.contains(game)) {
			games.remove(game);
			game.removeUser(this);
		}
	}
	
	public void addComment(Comment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			comment.setUser(this);
		}
	}
	
	public void removeComment(Comment comment) {
		if (comments != null && comments.contains(comment)) {
			comments.remove(comment);
		}
	}
	
	public void addFriend(User user) {
		if (friends == null) {
			friends = new ArrayList<>();
		}
		if (!friends.contains(user)) {
			friends.add(user);
			user.addFriend(this);
		}
	}
	
	public void removeFriend(User user) {
		if (friends != null && friends.contains(user)) {
			friends.remove(user);
			user.removeFriend(this);
		}
	}
	
	public void addReview(Review review) {
		if (reviews == null) {
			 reviews = new ArrayList<>();
		}
		if (!reviews.contains(review)) {
			reviews.add(review);
			review.setUser(this);
		}
	}
	
	public void removeReview(Review review) {
		if (reviews != null && reviews.contains(review)) {
			reviews.remove(review);
			review.setUser(null);
		}
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", profilePictureUrl=" + profilePictureUrl + ", addresses=" + addresses + ", homeAddress="
				+ homeAddress + ", gameEvents=" + gameEvents + ", games=" + games + ", comments=" + comments
				+ ", reviews=" + reviews + "]";
	}

}
