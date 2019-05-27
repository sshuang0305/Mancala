package nl.sogyo.mancala.domain;

public class Player {
	
	Player opponent;
	boolean myTurn;
	
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

}
