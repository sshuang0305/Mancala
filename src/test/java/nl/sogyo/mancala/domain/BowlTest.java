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
		int beads = cellNumber1.getNumberOfBeads();
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
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(1).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(2).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(3).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(4).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(5).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getNeighbour(6).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(7).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(8).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(9).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(10).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(11).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(12).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getNeighbour(13).getOwner());
	}
	
	@Test
	public void switchOwnerofBowlTest() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.getOwner().switchTurnBothPlayers();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
		assertEquals(false, cellNumber1.getNeighbour(1).getOwner().getMyTurn());
		assertEquals(false, cellNumber1.getNeighbour(6).getOwner().getMyTurn());
		cellNumber1.getOwner().switchTurnBothPlayers();
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
	}

	
	@Test
	public void distributeBeadsInMoveTest() {
		assertEquals(4, cellNumber1.getNumberOfBeads());
		cellNumber1.doMove();
		assertEquals(0, cellNumber1.getNumberOfBeads());
		assertEquals(5, cellNumber1.getNeighbour(1).getNumberOfBeads());
		assertEquals(5, cellNumber1.getNeighbour(2).getNumberOfBeads());
		assertEquals(5, cellNumber1.getNeighbour(3).getNumberOfBeads());
		assertEquals(5, cellNumber1.getNeighbour(4).getNumberOfBeads());
		assertEquals(4, cellNumber1.getNeighbour(5).getNumberOfBeads());
		assertEquals(0, cellNumber1.getNeighbour(6).getNumberOfBeads());
	}
	
	@Test
	public void distributeEndsInBowl() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.doMove();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
	}
	
	@Test
	public void getOppositeBowlTest () {
		assertEquals(cellNumber1.getNeighbour(12), cellNumber1.getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(11), cellNumber1.getNeighbour(1).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(10), cellNumber1.getNeighbour(2).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(9), cellNumber1.getNeighbour(3).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(8), cellNumber1.getNeighbour(4).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(7), cellNumber1.getNeighbour(5).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(5), cellNumber1.getNeighbour(7).getOppositeBowl());
		assertEquals(cellNumber1.getNeighbour(4), cellNumber1.getNeighbour(8).getOppositeBowl());
	}

	@Test
	public void stealTest() {
		assertEquals(4, cellNumber1.getNumberOfBeads());
		cellNumber1.stealBeadsOppositeCell();
		assertEquals(0, cellNumber1.getNumberOfBeads());
		assertEquals(0, cellNumber1.getNeighbour(12).getNumberOfBeads());
		assertEquals(8, cellNumber1.getNeighbour(6).getNumberOfBeads());
	}
	
	@Test
	public void stealScenarioTest() {
		assertEquals(4, cellNumber1.getNeighbour(4).getNumberOfBeads());
		cellNumber1.getNeighbour(4).doMove();
		assertEquals(0, cellNumber1.getNeighbour(4).getNumberOfBeads());
		cellNumber1.getNeighbour(7).doMove();
		cellNumber1.doMove();
		assertEquals(0, cellNumber1.getNeighbour(4).getNumberOfBeads());
		assertEquals(0, cellNumber1.getNeighbour(8).getNumberOfBeads());
		assertEquals(8, cellNumber1.getNeighbour(6).getNumberOfBeads());
	}
	
	@Test
	public void moveOnEmptyBowlTest() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.doMove();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
		cellNumber1.getNeighbour(1).doMove();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
	}
	
	@Test
	public void moveWhenNotYourTurnTest() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.doMove();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
	}

	@Test
	public void checkIfGameIsWonTest() {
		
		cellNumber1.emptyOwnBowl();
		cellNumber1.getNeighbour(1).emptyOwnBowl();
		cellNumber1.getNeighbour(2).emptyOwnBowl();
		cellNumber1.getNeighbour(3).emptyOwnBowl();
		cellNumber1.getNeighbour(4).emptyOwnBowl();
		
		assertEquals(0 ,cellNumber1.getNumberOfBeads());
		assertEquals(0 ,cellNumber1.getNeighbour(1).getNumberOfBeads());
		assertEquals(0 ,cellNumber1.getNeighbour(2).getNumberOfBeads());
		assertEquals(0 ,cellNumber1.getNeighbour(3).getNumberOfBeads());
		assertEquals(0 ,cellNumber1.getNeighbour(4).getNumberOfBeads());
		assertEquals(4 ,cellNumber1.getNeighbour(5).getNumberOfBeads());

		assertFalse(cellNumber1.getOwner().gameFinished);
		cellNumber1.getNeighbour(5).doMove();
		assertTrue(cellNumber1.getOwner().gameFinished);
		
	}
	
	@Test
	public void getMyKalahaTest() {
		assertEquals(cellNumber1.getNeighbour(6), cellNumber1.getNeighbour(1).getMyKalaha());
	}

	@Test
	public void getDistanceToKalahaTest() {
		assertEquals(6, cellNumber1.getDistanceToMyKalaha());
		assertEquals(5, cellNumber1.getNeighbour(1).getDistanceToMyKalaha());
		assertEquals(0, cellNumber1.getNeighbour(6).getDistanceToMyKalaha());
		assertEquals(5, cellNumber1.getNeighbour(8).getDistanceToMyKalaha());
	}
}
