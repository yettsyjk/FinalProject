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

class AlertTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;

	private Alert alert;
	
	
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
		alert = em.find(Alert.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		alert = null;
	}
	
	@Test
	@DisplayName("Snitch test verify not null")
	void testSnitchAndUserNotNull() {
		assertNotNull(alert);
		
	}
	
	
	@Test
	@DisplayName("mapping Alert content")
	void testAlert() {
		assertNotNull(alert);
		assertEquals("Alert! meow.", alert.getContent() );
	}
	
	

}
