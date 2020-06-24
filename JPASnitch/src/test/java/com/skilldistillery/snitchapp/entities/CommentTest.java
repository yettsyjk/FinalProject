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

class CommentTest {
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	
	private Comment comment;

	
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
		comment = em.find(Comment.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}
	
	@Test
	@DisplayName("mapping Comment")
	void testCommeny() {
		assertNotNull(comment);
		assertEquals("Ya'll crazy!!!", comment.getContent());
	}
	
	
	
	
	
	

}
