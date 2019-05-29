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


		if(n == 2 || n == (numberOfCells / 2 + 2)) {
			this.nextNeighbour = new Kalaha(n - 1, origin, owner);
			this.owner.setKalaha(this.nextNeighbour);

		}
		else {
			this.nextNeighbour = new Bowl(n - 1, origin, owner);
		}
	}
	

}
