package nl.sogyo.mancala.domain;

class Kalaha extends Cell {
	
	final static int numberOfCells = 14;
	final static int totalNumberOfBeads = 12 * 4;
	
	
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
	
	public int getScore() {
		
		Cell nextCell = this.getNextNeighbour();
		int numberOfBeadsOpponent = this.owner.opponent.getKalaha().getNumberOfBeads();
		
		while (!(nextCell instanceof Kalaha)) {
			numberOfBeadsOpponent += nextCell.getNumberOfBeads();
			nextCell = nextCell.getNextNeighbour();
		}
		
		return totalNumberOfBeads - numberOfBeadsOpponent;

	}

}
