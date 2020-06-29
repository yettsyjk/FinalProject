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

class CategoryTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	
	private Category category;
	
	
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
		category = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();	
		category = null;

	}
	@Test
	@DisplayName("Category Name field")
	void testCategoryName() {
		assertNotNull(category);
		assertEquals("Traffic", category.getName());
	}
	
	@Test
	@DisplayName("Category to Snitch ")
	void testCategoryToSnitch() {
		assertNotNull(category);
		assertNotNull(category.getSnitches());
		assertTrue(category.getSnitches().size() > 0);
		assertEquals("Angry Yetis on Main St", category.getSnitches().get(0).getTitle());
	}
	
	@Test
	@DisplayName("Category to Alert ")
	void testCategoryToAlert() {
		assertNotNull(category);
		assertNotNull(category.getAlerts());
		assertTrue(category.getAlerts().size() > 0);
		assertEquals("Slow moving traffic between Monument and Castle rock causing delays in commute.", category.getAlerts().get(0).getContent());
	}

}
