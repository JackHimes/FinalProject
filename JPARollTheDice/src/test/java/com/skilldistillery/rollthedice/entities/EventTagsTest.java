package com.skilldistillery.rollthedice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTagsTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventTag eventTag;

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
		eventTag = em.find(EventTag.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventTag = null;
	}

	@DisplayName("test Event Tag entity mapping")
	@Test
	void test1() {
		assertNotNull(eventTag);
		assertEquals("Alcohol Friendly", eventTag.getName());
	}

}
