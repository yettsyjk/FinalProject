package com.skilldistillery.snitchapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
		private static  EntityManagerFactory emf;
		private static EntityManager em;
		
		private User user;
		
	
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("snitchPU");
		
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
	@DisplayName("not null verified")
	void testUser() {
		assertNotNull(user);
	}
	

	@Test
	@DisplayName("User mapping to fields")
	void testUserEntityMapping() {
		
		assertNotNull(user);
		assertEquals("admin@admin.com", user.getEmail());
		assertEquals("admin", user.getUsername());
		assertEquals(1, user.getId());
	}
	
	@Test
	@DisplayName("user mapping to alert")
	void testUserMapping() {
		assertNotNull(user);
		assertNotNull(user.getAlerts());
		assertTrue(user.getAlerts().size() > 0);
		assertEquals("Alert! meow.", user.getAlerts().get(0).getContent());
	}
	
	@Test
	@DisplayName("user mapping to snitch")
	void testUserMappingToSnitch() {
		assertNotNull(user);
		assertNotNull(user.getSnitchesCreated() );
		assertTrue(user.getSnitchesCreated().size() == 0);
//		assertEquals("Angry Yetis on Main St", user.getSnitchesCreated().get(0).getTitle());
	}

	
	@Test
	@DisplayName("user mapping to snitchVote")
	void testUserMappingToSnitchVote() {
		assertNotNull(user);
		assertNotNull(user.getVotes() );
		assertTrue(user.getVotes().size() == 0);
		
	}
	
	@Test
	@DisplayName("user mapping to comment")
	void testUserMappingToComment() {
		assertNotNull(user);
		assertNotNull(user.getComments() );
		assertTrue(user.getComments().size() == 0);
//		assertEquals("Ya'll crazy!!!", user.getComments().get(0).getContent());
	}

	
	
	
}
