package com.skilldistillery.rollthedice.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	
	@Column(name="number_of_guests")
	private int numberOfGuests;
	
	private boolean alcohol;
	
	private boolean food;

	private boolean kids;
	
	private boolean pets;
	
	private boolean enabled;

	public Event() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public boolean isAlcohol() {
		return alcohol;
	}

	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}

	public boolean isKids() {
		return kids;
	}

	public void setKids(boolean kids) {
		this.kids = kids;
	}

	public boolean isPets() {
		return pets;
	}

	public void setPets(boolean pets) {
		this.pets = pets;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alcohol, date, enabled, food, id, kids, numberOfGuests, pets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return alcohol == other.alcohol && Objects.equals(date, other.date) && enabled == other.enabled
				&& food == other.food && id == other.id && kids == other.kids && numberOfGuests == other.numberOfGuests
				&& pets == other.pets;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", date=" + date + ", numberOfGuests=" + numberOfGuests + ", alcohol=" + alcohol
				+ ", food=" + food + ", kids=" + kids + ", pets=" + pets + ", enabled=" + enabled + "]";
	}
	
	
	
	
	
	
}
