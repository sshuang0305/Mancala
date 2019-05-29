package nl.sogyo.mancala.domain;

class Bowl extends Cell {
	
	final static int numberOfCells = 14;
	
	public Bowl() {
		
		this.owner = new Player();
		this.nextNeighbour = new Bowl(numberOfCells - 1, this, owner);

	}
	
	Bowl(int n, Bowl origin, Player owner) {
		
		if (n < 8) {
			this.owner = owner.getOpponent();
		}
		else {
			this.owner = owner;
		}


		if(n == (numberOfCells / 2 + 2) || n == 2) {
			this.nextNeighbour = new Kalaha(n - 1, origin, owner);
		}
		else {
			this.nextNeighbour = new Bowl(n - 1, origin, owner);
		}
	}
	

}
