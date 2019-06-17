/**
 * Player.java
 *
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

public class Player {
	
	private boolean myTurn;
	private Player opponent;
	protected boolean gameFinished = false;

	/**
	 * Player constructor to create opponent.
	 * Initializes turn of the player and creates an opponent.
	 */
	Player () {
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	/**
	 * Player constructor to set opponent.
	 * Initializes turn of the player and sets an opponent.
	 * @param myOpponent
	 */
	Player(Player myOpponent) {
		this.myTurn = false;
		this.opponent = myOpponent;
	}
	
	public boolean getMyTurn() {
		return this.myTurn;
	}

	public void setMyTurn(boolean turn) {
		this.myTurn = turn;
	}

	public Player getOpponent() {
		return this.opponent;
	}

	/**
	 * Switches the turn of the players.
	 */
	public void switchTurnBothPlayers() {
		this.opponent.myTurn = myTurn;
		this.myTurn = !myTurn;
	}
}
