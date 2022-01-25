package com.skilldistillery.rollthedice.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private int timeToPlay;
	
	private String description;
	
	@Column(name="image_url")
	private String imageUrl;

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

	public int getTimeToPlay() {
		return timeToPlay;
	}

	public void setTimeToPlay(int timeToPlay) {
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
