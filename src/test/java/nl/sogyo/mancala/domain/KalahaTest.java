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
		firstKalaha = (Kalaha) cellNumber1.getNeighbour(6);
		secondKalaha = (Kalaha) cellNumber1.getNeighbour(13);
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
		assertEquals(0, firstKalaha.numberOfBeads);
		assertEquals(0, secondKalaha.numberOfBeads);
	}
	
	@Test
	public void getOwnerTest() {
		assertEquals(cellNumber1.owner, firstKalaha.owner);
		assertEquals(cellNumber1.owner.opponent, secondKalaha.owner);
	}
	
	@Test (expected = RuntimeException.class)
	public void doMoveOnKalahaTest() {
		firstKalaha.doMove();
	}
	
	@Test (expected = RuntimeException.class)
	public void stealFromKalahaTest() {
		firstKalaha.steal();
	}
	
	@Test
	public void distributeEndsInOwnKalahaTest() {
		assertEquals(true, cellNumber1.getNeighbour(2).owner.myTurn);
		((Bowl) cellNumber1.getNeighbour(2)).doMove();
		assertEquals(true, cellNumber1.owner.myTurn);
	}

	@Test
	public void distributePassingOpponentsKalahaTest() {
		cellNumber1.getNeighbour(1).doMove();
		assertEquals(4, cellNumber1.getNeighbour(12).numberOfBeads);
		cellNumber1.getNeighbour(10).doMove();
		assertEquals(0, cellNumber1.getNeighbour(10).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(11).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(12).numberOfBeads);
		assertEquals(1, cellNumber1.getNeighbour(13).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(14).numberOfBeads);
		assertEquals(0, cellNumber1.getNeighbour(15).numberOfBeads);
	}
	
	@Test
	public void distributePassingOwnKalahaTest() {
		assertEquals(4, cellNumber1.getNeighbour(5).numberOfBeads);
		cellNumber1.getNeighbour(5).doMove();
		assertEquals(0, cellNumber1.getNeighbour(5).numberOfBeads);
		assertEquals(1, cellNumber1.getNeighbour(6).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(7).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(8).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(9).numberOfBeads);
		assertEquals(4, cellNumber1.getNeighbour(10).numberOfBeads);
	}
	
	@Test
	public void getScoreTest() {
		cellNumber1.getNeighbour(3).doMove();
		assertEquals(23, firstKalaha.getScore());
		assertEquals(25, secondKalaha.getScore());
	}

}
