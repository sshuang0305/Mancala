package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player player;
	Bowl cellNumber1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player();
		cellNumber1 = new Bowl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		player = null;
		cellNumber1 = null;
	}

	@Test
	public void getPlayer() {
		boolean myTurn = player.myTurn;
		assertEquals(true, myTurn);
	}
	
	@Test
	public void getOpponent() {
		boolean opponentTurn = player.opponent.myTurn;
		Player opponentsOpponent = player.opponent.opponent;
		assertEquals(false, opponentTurn);
		assertEquals(player, opponentsOpponent);
	}
	
	@Test
	public void switchOwnTurnTest() {
		assertEquals(true, player.myTurn);
		assertEquals(false, player.opponent.myTurn);
		player.switchTurnBothPlayers();
		assertEquals(false, player.myTurn);
		assertEquals(true, player.opponent.myTurn);
	}
	
	@Test
	public void getKalahaOfPlayer() {
		assertEquals(cellNumber1.getNeighbour(6), cellNumber1.owner.getKalaha());
		assertEquals(cellNumber1.getNeighbour(13), cellNumber1.owner.opponent.getKalaha());
		assertTrue(cellNumber1.owner.getKalaha() instanceof Kalaha);
		assertTrue(cellNumber1.owner.opponent.getKalaha() instanceof Kalaha);
	}
}
