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
	public void getNumberofBeadsTest() {

		int beads = cellNumber1.getNumberOfBeads();
		assertEquals(4, beads);
	}
	
	@Test
	public void getFourteenCellsTest() {

		int domainSize = cellNumber1.getSizeOfDomain(cellNumber1);
		assertEquals(14, domainSize);
	}
	
	@Test
	public void getOwnerOfBowlsTest() {
		
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(1).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(2).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(3).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(4).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(5).getOwner());
		assertEquals(cellNumber1.getOwner(), cellNumber1.getANeighbour(6).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(7).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(8).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(9).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(10).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(11).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(12).getOwner());
		assertEquals(cellNumber1.getOwner().getOpponent(), cellNumber1.getANeighbour(13).getOwner());

	}
	
	@Test
	public void switchOwnerofBowlTest() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.getOwner().switchTurnBothPlayers();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
		assertEquals(false, cellNumber1.getANeighbour(1).getOwner().getMyTurn());
		assertEquals(false, cellNumber1.getANeighbour(6).getOwner().getMyTurn());
		cellNumber1.getOwner().switchTurnBothPlayers();
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
	}

	
	@Test
	public void distributeBeadsInMoveTest() {
		assertEquals(4, cellNumber1.getNumberOfBeads());
		cellNumber1.doMove();
		assertEquals(0, cellNumber1.getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(1).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(2).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(3).getNumberOfBeads());
		assertEquals(5, cellNumber1.getANeighbour(4).getNumberOfBeads());
		assertEquals(4, cellNumber1.getANeighbour(5).getNumberOfBeads());
		assertEquals(0, cellNumber1.getANeighbour(6).getNumberOfBeads());
	}
	
	@Test
	public void distributeEndsInBowl() {
		assertEquals(true, cellNumber1.getOwner().getMyTurn());
		cellNumber1.doMove();
		assertEquals(false, cellNumber1.getOwner().getMyTurn());
	}
	
	@Test
	public void getOppositeCell () {
		assertEquals(cellNumber1.getANeighbour(12), cellNumber1.getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(11), cellNumber1.getANeighbour(1).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(10), cellNumber1.getANeighbour(2).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(9), cellNumber1.getANeighbour(3).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(8), cellNumber1.getANeighbour(4).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(7), cellNumber1.getANeighbour(5).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(13), cellNumber1.getANeighbour(6).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(5), cellNumber1.getANeighbour(7).getOppositeCell());
		assertEquals(cellNumber1.getANeighbour(4), cellNumber1.getANeighbour(8).getOppositeCell());
	}


	@Test
	public void stealTest() {
		assertEquals(4, cellNumber1.getNumberOfBeads());
		cellNumber1.steal();
		assertEquals(0, cellNumber1.getNumberOfBeads());
		assertEquals(0, cellNumber1.getANeighbour(12).getNumberOfBeads());
		assertEquals(8, cellNumber1.getANeighbour(6).getNumberOfBeads());
	}
	
	@Test (expected = RuntimeException.class)
	public void moveInvalidTest() {
		cellNumber1.doMove();
		cellNumber1.getANeighbour(1).doMove();
	}
	
	@Test
	public void checkIfGameIsWon() {
		cellNumber1.getANeighbour(7).emptyBowl();
		cellNumber1.getANeighbour(8).emptyBowl();
		cellNumber1.getANeighbour(9).emptyBowl();
		cellNumber1.getANeighbour(10).emptyBowl();
		cellNumber1.getANeighbour(11).emptyBowl();
		cellNumber1.getANeighbour(12).emptyBowl();
		cellNumber1.getANeighbour(1).doMove();
	}
}
