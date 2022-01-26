package com.skilldistillery.rollthedice.entities;

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

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="max_players")
	private int maxPlayers;
	
	@Column(name="link_to_game")
	private String linkToGame;
	
	@Column(name="time_to_play")
	private String timeToPlay;
	
	private String description;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@ManyToMany
	@JoinTable(name="game_event_has_game",
			joinColumns=@JoinColumn(name="game_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> users;

	@ManyToMany
	@JoinTable(name="user_has_game",
	joinColumns=@JoinColumn(name="game_id"),
	inverseJoinColumns=@JoinColumn(name="game_event_id"))
	private List<GameEvent> gameEvents;
	
	@ManyToMany(mappedBy="games")
	private List<Genre> genres;

	public Game() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getLinkToGame() {
		return linkToGame;
	}

	public void setLinkToGame(String linkToGame) {
		this.linkToGame = linkToGame;
	}

	public String getTimeToPlay() {
		return timeToPlay;
	}

	public void setTimeToPlay(String timeToPlay) {
		this.timeToPlay = timeToPlay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<GameEvent> getGameEvents() {
		return gameEvents;
	}

	public void setGameEvents(List<GameEvent> gameEvents) {
		this.gameEvents = gameEvents;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", maxPlayers=" + maxPlayers + ", linkToGame=" + linkToGame
				+ ", timeToPlay=" + timeToPlay + ", description=" + description + ", imageUrl=" + imageUrl + "]";
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
		Game other = (Game) obj;
		return id == other.id;
	}

}
