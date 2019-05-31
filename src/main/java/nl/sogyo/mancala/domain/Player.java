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
	private Cell myKalaha;
	private boolean allMyBowlsEmpty = true;
	protected boolean gameFinished;

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

	/**
	 * Sets the kalaha of the player.
	 * @param kalaha	The kalaha of the player
	 */
	public void setKalaha(Cell kalaha) {
		this.myKalaha = kalaha;
	}
	
	/**
	 * Gets the kalaha of the player.
	 * @return	The kalaha of the player
	 */
	public Cell getKalaha() {
		return this.myKalaha;
	}
	
	/**
	 * Checks if game is finished by checking if the bowls of one
	 * of the players are empty.
	 */
	public void checkIfGameFinished() {

		Cell myCell = this.opponent.myKalaha.getNextNeighbour();

		while (!(myCell instanceof Kalaha)) {
			
			if (myCell.getNumberOfBeads() != 0) {
				this.allMyBowlsEmpty = false;
			}
			myCell = myCell.getNextNeighbour();
		}

		Cell oppCell = this.myKalaha.getNextNeighbour();
		
		while (!(oppCell instanceof Kalaha)) {

			if (oppCell.getNumberOfBeads() != 0) {
				this.opponent.allMyBowlsEmpty = false;
			}
			oppCell = oppCell.getNextNeighbour();
			if (oppCell.getNumberOfBeads() != 0) {
				this.opponent.allMyBowlsEmpty = false;
			}
		}
		
		if (this.allMyBowlsEmpty || this.opponent.allMyBowlsEmpty) {
			this.gameFinished = true;
			this.opponent.gameFinished = true;
		}
	}

}
