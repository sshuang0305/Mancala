package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {
	
	Player player;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		player = null;
	}

	@Test
	public void getPlayer() {

		String name = player.getName();
		boolean myTurn = player.getMyTurn();

		assertEquals("A", name);
		assertEquals(true, myTurn);
	}
	
	@Test
	public void getOpponent() {
		String opponentName = player.getOpponent().getName();
		boolean opponentTurn = player.getOpponent().getMyTurn();
		Player opponentsOpponent = player.getOpponent().getOpponent();
		assertEquals("B", opponentName);
		assertEquals(false, opponentTurn);
		assertEquals(player, opponentsOpponent);
	}

}
