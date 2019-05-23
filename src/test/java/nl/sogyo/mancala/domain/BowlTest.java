package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlTest {
	
	Bowl firstBowl;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		firstBowl = new Bowl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		firstBowl = null;
	}
	

	@Test
	public void getNumberofBeadsTest() {

		int beads = firstBowl.getNumberOfBeads();
		assertEquals(4, beads);
	}
	
	@Test
	public void getNextNeighbourTest() {
		Bowl nextBowl = firstBowl.getNextNeighbour();
		assertNotNull(nextBowl);
	}

}
