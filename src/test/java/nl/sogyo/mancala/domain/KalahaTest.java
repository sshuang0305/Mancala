package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KalahaTest {
	
	Bowl cellNumber1;
	Kalaha firstKalaha;
	Kalaha secondKalaha;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cellNumber1 = new Bowl();
		firstKalaha = (Kalaha) cellNumber1.getANeighbour(6);
		secondKalaha = (Kalaha) cellNumber1.getANeighbour(13);
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
	
	@Test
	public void distributeEndsInOwnKalahaTest() {
		assertEquals(true, cellNumber1.getANeighbour(2).getOwner().getMyTurn());
		cellNumber1.getANeighbour(2).doMove();
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
	}


	@Test
	public void distributePassingOpponentsKalahaTest() {
		cellNumber1.getANeighbour(1).doMove();
		assertEquals(4, cellNumber1.getANeighbour(12).getNumberOfBeads());
		cellNumber1.getANeighbour(10).doMove();
		assertEquals(0, cellNumber1.getANeighbour(10).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(11).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(12).getNumberOfBeads());
		assertEquals(1, cellNumber1.getANeighbour(13).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(14).getNumberOfBeads());
		assertEquals(0, cellNumber1.getANeighbour(15).getNumberOfBeads());

	}
	
	@Test
	public void distributePassingOwnKalahaTest() {
		assertEquals(4, cellNumber1.getANeighbour(5).getNumberOfBeads());
		cellNumber1.getANeighbour(5).doMove();
		assertEquals(0, cellNumber1.getANeighbour(5).getNumberOfBeads());
		assertEquals(1, cellNumber1.getANeighbour(6).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(7).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(8).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(9).getNumberOfBeads());
		assertEquals(4, cellNumber1.getANeighbour(10).getNumberOfBeads());
	}
	
	@Test
	public void getScoreTest() {
		cellNumber1.getANeighbour(3).doMove();
		assertEquals(23, firstKalaha.getScore());
		assertEquals(25, secondKalaha.getScore());
	}

}
