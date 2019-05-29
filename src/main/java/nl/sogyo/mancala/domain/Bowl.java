package nl.sogyo.mancala.domain;

class Bowl extends Cell {

	final static int STARTING_NO_BEADS = 4;
	
	public Bowl() {
		this.numberOfBeads = STARTING_NO_BEADS;
		this.owner = new Player();
		this.nextNeighbour = new Bowl(numberOfCells - 1, this, owner);

	}
	
	Bowl(int n, Bowl origin, Player owner) {
		this.numberOfBeads = STARTING_NO_BEADS;
		
		if (n < 8) {
			this.owner = owner.getOpponent();
		}
		else {
			this.owner = owner;
		}


		if(n == 2 || n == (numberOfCells / 2 + 2)) {
			this.nextNeighbour = new Kalaha(n - 1, origin, owner);
			this.owner.setKalaha(this.nextNeighbour);

		}
		else {
			this.nextNeighbour = new Bowl(n - 1, origin, owner);
		}
	}
	

}
