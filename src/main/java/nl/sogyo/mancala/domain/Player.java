package nl.sogyo.mancala.domain;

public class Player {
	
	String name;
	Player opponent;
	boolean myTurn;
	
	Player () {
		this.name = "A";
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player myOpponent) {
		this.name = "B";
		this.myTurn = false;
		this.opponent = myOpponent;
	}
	
	public String getName() {
		return name;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public boolean getMyTurn() {
		return myTurn;
	}

}
