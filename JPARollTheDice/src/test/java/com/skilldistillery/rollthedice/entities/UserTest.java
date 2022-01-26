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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	void test_User_to_addresses_ManyToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getAddresses());
		assertTrue(user.getAddresses().size() > 0);
	}
	
	@Test
	void test_User_to_homeAddress_OneToOne_mapping() {
		assertNotNull(user);
		assertEquals("1234 Main St", user.getHomeAddress().getAddress());
	}
	
	@Test
	void test_User_to_gameEvents_ManyToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getGameEvents());
		assertTrue(user.getGameEvents().size() > 0);
	}
	
	@Test
	void test_User_to_hostedGameEvents_OneToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getHostedGameEvents());
		assertTrue(user.getHostedGameEvents().size() > 0);
	}
	
	@Test
	void test_User_to_games_ManyToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getGames());
		assertTrue(user.getGames().size() > 0);
	}
	
	@Test
	void test_User_to_comments_OneToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getComments());
		assertTrue(user.getComments().size() > 0);
	}
	
	@Test
	void test_User_to_reviews_OneToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getReviews());
		assertTrue(user.getReviews().size() > 0);
	}
	
	@Test
	void test_User_to_friends_ManyToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFriends());
		assertTrue(user.getFriends().size() > 0);
	}

}
