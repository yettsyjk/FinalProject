package com.skilldistillery.snitchapp.entities;

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

class SnitchTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	
	
	private Snitch snitch;
	
	
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
	
		snitch = em.find(Snitch.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		snitch = null;
	}
	
	@Test
	@DisplayName("Snitch test verify not null")
	void testSnitchAndUser() {
		assertNotNull(snitch);
	}
	
	
	@Test
	@DisplayName("testing Snitch ")
	void testSnitchFields() {
		assertEquals("Angry Yetis on Main St", snitch.getTitle());
		assertEquals("There is a wild animal prowling around downtown, yelling System.exit() at everyone", snitch.getDescription());
	}
	
	@Test
	@DisplayName("Snitch To User mapping")
	void testSnitchToUser() {
		assertNotNull(snitch);
		assertNotNull(snitch.getUser());
		assertEquals("user", snitch.getUser().getUsername());
		
	}
	@Test
	@DisplayName("Snitch To Comment mapping")
	void testSnitchToComment() {
		assertNotNull(snitch);
		assertNotNull(snitch.getComments());
		assertTrue(snitch.getComments().size() > 0 );
		assertEquals("Ya'll crazy!!!", snitch.getComments().get(0).getContent());
	}
	
	@Test
	@DisplayName("Snitch To Address mapping")
	void testSnitchToAddress() {
		assertNotNull(snitch);
		assertNotNull(snitch.getAddress());
		assertEquals("123 Main St", snitch.getAddress().getStreet());
	}
	
	@Test
	@DisplayName("Snitch To Category mapping")
	void testSnitchToCategory() {
		assertNotNull(snitch);
		assertNotNull(snitch.getCategory());
		assertEquals("Traffic", snitch.getCategory().getName());
	}
	

}
