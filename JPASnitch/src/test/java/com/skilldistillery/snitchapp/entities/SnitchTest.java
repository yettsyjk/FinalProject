package com.skilldistillery.snitchapp.entities;

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
		assertEquals("There is a wild animal prowling around downtown, yelling System.exit() at everyone", snitch.getDescription);
	}
	
	
	
	

}
