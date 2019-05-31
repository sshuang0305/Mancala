package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlTest {
	
	Bowl cellNumber1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cellNumber1 = new Bowl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cellNumber1 = null;
	}

	@Test
	public void getNumberOfBeadsInBowlTest() {
		int beads = cellNumber1.numberOfBeads;
		assertEquals(4, beads);
	}
	
	@Test
	public void getFourteenCellsTest() {
		int domainSize = cellNumber1.getSizeOfDomain();
		assertEquals(14, domainSize);
		assertEquals(14, cellNumber1.getNeighbour(4).getSizeOfDomain());
	}
	
	@Test
	public void getOwnerOfBowlsTest() {
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(1).owner);
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(2).owner);
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(3).owner);
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(4).owner);
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(5).owner);
		assertEquals(cellNumber1.owner, cellNumber1.getNeighbour(6).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(7).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(8).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(9).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(10).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(11).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(12).owner);
		assertEquals(cellNumber1.owner.opponent, cellNumber1.getNeighbour(13).owner);
	}
	
	@Test
	public void switchOwnerofBowlTest() {
		assertEquals(true, cellNumber1.owner.myTurn);
		cellNumber1.owner.switchTurnBothPlayers();
		assertEquals(false, cellNumber1.owner.myTurn);
		assertEquals(false, cellNumber1.getNeighbour(1).owner.myTurn);
		assertEquals(false, cellNumber1.getNeighbour(6).owner.myTurn);
		cellNumber1.owner.switchTurnBothPlayers();
		assertEquals(true, cellNumber1.owner.myTurn);
	}

	
	@Test
	public void distributeBeadsInMoveTest() {
		assertEquals(4, cellNumber1.numberOfBeads);
		cellNumber1.doMove();
		assertEquals(0, cellNumber1.numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(1).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(2).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(3).numberOfBeads);
		assertEquals(5, cellNumber1.getNeighbour(4).numberOfBeads);
		assertEquals(4, cellNumber1.getNeighbour(5).numberOfBeads);
		assertEquals(0, cellNumber1.getNeighbour(6).numberOfBeads);
	}
	
	@Test
	public void distributeEndsInBowl() {
		assertEquals(true, cellNumber1.owner.myTurn);
		cellNumber1.doMove();
		assertEquals(false, cellNumber1.owner.myTurn);
	}
	
	@Test
	public void getOppositeCell () {
		assertEquals(cellNumber1.getNeighbour(12), cellNumber1.getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(11), cellNumber1.getNeighbour(1).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(10), cellNumber1.getNeighbour(2).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(9), cellNumber1.getNeighbour(3).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(8), cellNumber1.getNeighbour(4).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(7), cellNumber1.getNeighbour(5).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(13), cellNumber1.getNeighbour(6).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(5), cellNumber1.getNeighbour(7).getOppositeCell());
		assertEquals(cellNumber1.getNeighbour(4), cellNumber1.getNeighbour(8).getOppositeCell());
	}

	@Test
	public void stealTest() {
		assertEquals(4, cellNumber1.numberOfBeads);
		cellNumber1.stealBeadsOppositeCell();
		assertEquals(0, cellNumber1.numberOfBeads);
		assertEquals(0, cellNumber1.getNeighbour(12).numberOfBeads);
		assertEquals(8, cellNumber1.getNeighbour(6).numberOfBeads);
	}
	
	@Test
	public void stealScenarioTest() {
		assertEquals(4, cellNumber1.getNeighbour(4).numberOfBeads);
		cellNumber1.getNeighbour(4).doMove();
		assertEquals(0, cellNumber1.getNeighbour(4).numberOfBeads);
		cellNumber1.getNeighbour(7).doMove();
		cellNumber1.doMove();
		assertEquals(0, cellNumber1.getNeighbour(4).numberOfBeads);
		assertEquals(0, cellNumber1.getNeighbour(8).numberOfBeads);
		assertEquals(8, cellNumber1.getNeighbour(6).numberOfBeads);
	}
	
	@Test (expected = RuntimeException.class)
	public void moveInvalidTest() {
		cellNumber1.doMove();
		cellNumber1.getNeighbour(1).doMove();
	}
	
	@Test (expected = RuntimeException.class)
	public void moveInvalidTest2() {
		cellNumber1.doMove();
		cellNumber1.getNeighbour(7).doMove();
		cellNumber1.getNeighbour(1).doMove();
		cellNumber1.getNeighbour(8).doMove();
	}

	@Test
	public void checkIfGameIsWonTest() {
		
		cellNumber1.emptyOwnBowl();
		cellNumber1.getNeighbour(1).emptyOwnBowl();
		cellNumber1.getNeighbour(2).emptyOwnBowl();
		cellNumber1.getNeighbour(3).emptyOwnBowl();
		cellNumber1.getNeighbour(4).emptyOwnBowl();
		
		assertEquals(0 ,cellNumber1.numberOfBeads);
		assertEquals(0 ,cellNumber1.getNeighbour(1).numberOfBeads);
		assertEquals(0 ,cellNumber1.getNeighbour(2).numberOfBeads);
		assertEquals(0 ,cellNumber1.getNeighbour(3).numberOfBeads);
		assertEquals(0 ,cellNumber1.getNeighbour(4).numberOfBeads);
		assertEquals(4 ,cellNumber1.getNeighbour(5).numberOfBeads);

		assertFalse(cellNumber1.owner.gameFinished);
		cellNumber1.getNeighbour(5).doMove();
		assertTrue(cellNumber1.owner.gameFinished);
		
	}
}
