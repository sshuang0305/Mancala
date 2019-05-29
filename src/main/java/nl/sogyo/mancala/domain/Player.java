package nl.sogyo.mancala.domain;

public class Player {
	
	Player opponent;
	boolean myTurn;
	Cell myKalaha;
	boolean bowlsEmpty = true;
	boolean gameFinished;

	Player () {
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player myOpponent) {
		this.myTurn = false;
		this.opponent = myOpponent;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public boolean getMyTurn() {
		return myTurn;
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

		Cell myCell = this.opponent.myKalaha.getNextNeighbour();

		while (!(myCell instanceof Kalaha)) {
			
			if (myCell.getNumberOfBeads() != 0) {
				this.bowlsEmpty = false;
			}
			myCell = myCell.nextNeighbour;
		}

		Cell oppCell = this.myKalaha.getNextNeighbour();
		
		while (!(oppCell instanceof Kalaha)) {

			if (oppCell.getNumberOfBeads() != 0) {
				this.opponent.bowlsEmpty = false;
			}
			oppCell = oppCell.nextNeighbour;
			if (oppCell.getNumberOfBeads() != 0) {
				this.opponent.bowlsEmpty = false;
			}
		}
		
		if (this.bowlsEmpty || this.opponent.bowlsEmpty) {
			this.gameFinished = true;
			this.opponent.gameFinished = true;
		}
	}

}
