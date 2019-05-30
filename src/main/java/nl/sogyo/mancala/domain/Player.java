/**
 * Player.java
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

public class Player {
	
	protected boolean myTurn;
	protected Player opponent;
	protected Cell myKalaha;
	protected boolean allMyBowlsEmpty = true;
	protected boolean gameFinished;

	Player () {
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player myOpponent) {
		this.myTurn = false;
		this.opponent = myOpponent;
	}

	public void switchTurnBothPlayers() {
		this.opponent.myTurn = myTurn;
		this.myTurn = !myTurn;
	}

	public void setKalaha(Cell kalaha) {
		this.myKalaha = kalaha;
	}
	
	public Cell getKalaha() {
		return this.myKalaha;
	}
	
	public void checkIfGameFinished() {

		Cell myCell = this.opponent.myKalaha.nextNeighbour;

		while (!(myCell instanceof Kalaha)) {
			
			if (myCell.numberOfBeads != 0) {
				this.allMyBowlsEmpty = false;
			}
			myCell = myCell.nextNeighbour;
		}

		Cell oppCell = this.myKalaha.nextNeighbour;
		
		while (!(oppCell instanceof Kalaha)) {

			if (oppCell.numberOfBeads != 0) {
				this.opponent.allMyBowlsEmpty = false;
			}
			oppCell = oppCell.nextNeighbour;
			if (oppCell.numberOfBeads != 0) {
				this.opponent.allMyBowlsEmpty = false;
			}
		}
		
		if (this.allMyBowlsEmpty || this.opponent.allMyBowlsEmpty) {
			this.gameFinished = true;
			this.opponent.gameFinished = true;
		}
	}

}
