package com.skilldistillery.rollthedice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Game game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPARollTheDice");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		game = em.find(Game.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}

	@DisplayName("test game entity mapping")
	@Test
	void test1() {
		assertNotNull(game);
		assertEquals("Ticket to Ride", game.getName());
	}
	
	@DisplayName("test game to genre many to many relationship mapping")
	@Test
	void test2() {
		assertNotNull(game);
		assertTrue(game.getGenres().size() > 0);
		assertEquals("Dice", game.getGenres().get(0).getName());
	}

	@DisplayName("test game to game event many to many relationship mapping")
	@Test
	void test3() {
		assertNotNull(game);
		assertTrue(game.getGameEvents().size() > 0);
		assertEquals("Best Game Night", game.getGameEvents().get(0).getTitle());
	}
	
	@DisplayName("test game to user many to many relationship mapping")
	@Test
	void test5() {
		assertNotNull(game);
		assertTrue(game.getUsers().size() > 0);
		assertEquals("admin", game.getUsers().get(0).getUsername());
	}

}
