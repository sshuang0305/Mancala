package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KalahaTest {
	
	Bowl cellNumber1;
	Cell firstKalaha;
	Cell secondKalaha;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cellNumber1 = new Bowl();
		firstKalaha = cellNumber1.getANeighbour(6);
		secondKalaha = cellNumber1.getANeighbour(13);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cellNumber1 = null;
		firstKalaha = null;
		secondKalaha = null;
	}

	@Test
	public void kahalaExistsTest() {
		assertTrue(firstKalaha instanceof Kalaha);
		assertTrue(secondKalaha instanceof Kalaha);
	}

	@Test
	public void getNumberofBeadsTest() {
		assertEquals(0, firstKalaha.getNumberOfBeads());
		assertEquals(0, secondKalaha.getNumberOfBeads());
	}
	
	@Test
	public void getOwnerTest() {
		assertEquals(cellNumber1.getOwner(), firstKalaha.getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), secondKalaha.getOwner());
	}
	
	@Test (expected = RuntimeException.class)
	public void doMoveOnKalahaTest() {
		firstKalaha.doMove();
	}

}
