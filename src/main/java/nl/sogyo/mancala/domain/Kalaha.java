package nl.sogyo.mancala.domain;

class Kalaha extends Cell {
	
	final static int numberOfCells = 14;
	
	
	public Kalaha(int n, Bowl origin, Player owner) {
		this.numberOfBeads = 0;
		
		if (n == 8) {
			this.owner = owner;
			this.nextNeighbour = new Bowl(n - 1, origin, owner);
		}
		else {
			this.owner = owner.getOpponent();
			this.nextNeighbour = origin;
		}
	}
	
	public void doMove() {
		throw new RuntimeException("Cannot do a move on a Kalaha.");
	}

}
