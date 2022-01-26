package com.skilldistillery.rollthedice.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameEventTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private GameEvent gameEvent;

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
		gameEvent = em.find(GameEvent.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		gameEvent = null;
	}

	@Test
	void test_GameEvent_entity_mapping() {
		assertNotNull(gameEvent);
		assertEquals("Best Game Night", gameEvent.getTitle());
	}

}
