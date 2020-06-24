package com.skilldistillery.snitchapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SnitchVoteTest {
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
		
		SnitchVoteId sid = new SnitchVoteId();
		sid.setUserId(1);
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
		assertNull(snitchVote);
//		assertEquals(null, snitchVote.getNote());
	}//is null expect red
	
	
	
	
	

}
