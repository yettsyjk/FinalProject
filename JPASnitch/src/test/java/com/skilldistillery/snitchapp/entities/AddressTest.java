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

class AddressTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	
	private Address address;
	
	
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
		address = em.find(Address.class, 1);

	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}
	
	
	@Test
	@DisplayName("mapping Address not null")
	void testAlert() {
		assertNotNull(address);
	}
	
	@Test
	@DisplayName("mapping Address fields")
	void testAddress() {
		assertNotNull(address);
		assertEquals("123 Main St", address.getStreet() );
		assertEquals("Parker", address.getCity() );
		assertEquals(80138, address.getZip() );
	}
	
	@Test
	@DisplayName("mapping Address to Snitch")
	void testAlertSnitch() {
		assertNotNull(address);
		assertNotNull(address.getSnitches());
		assertTrue(address.getSnitches().size() > 0);
	}
	

}
