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

class SnitchVote {
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	
	private SnitchVote snitchVote;
	
	
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
		
		SnitchVote sid = new SnitchVote();
		sid.setUserId(2);
		sid.setSnitchId(1);
		snitchVote = em.find(SnitchVote.class, sid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		snitchVote = null;
	}
	@Test
	@DisplayName("SnitchVote test verify not null")
	void testSnitchVoter() {
//		assertNotNull(snitchVote);
		assertEquals(0, snitchVote.getNote().size());
	}//is null expect red
	
	
	
	
	

}
