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
		boolean myTurn = player.getMyTurn();
		assertEquals(true, myTurn);
	}
	
	@Test
	public void getOpponent() {
		boolean opponentTurn = player.getOpponent().getMyTurn();
		Player opponentsOpponent = player.getOpponent().getOpponent();
		assertEquals(false, opponentTurn);
		assertEquals(player, opponentsOpponent);
	}
	
	@Test
	public void switchOwnTurnTest() {
		assertEquals(true, player.getMyTurn());
		assertEquals(false, player.getOpponent().getMyTurn());
		player.switchTurnBothPlayers();
		assertEquals(false, player.getMyTurn());
		assertEquals(true, player.getOpponent().getMyTurn());
	}
}
