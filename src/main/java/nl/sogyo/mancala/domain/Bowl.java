package nl.sogyo.mancala.domain;

class Bowl extends Cell {
	
	final static int numberOfCells = 14;


	
	public Bowl() {
		this.nextNeighbour = new Kalaha(numberOfCells, this);
	}
	
	Bowl(int n, Bowl origin) {

		if (n == 2) {
			this.nextNeighbour = origin;
		}
		else if(n == (numberOfCells / 2 + 1)) {
			this.nextNeighbour = new Kalaha(n - 1, origin);
		}
		else {
			this.nextNeighbour = new Bowl(n - 1, origin);
		}
	}
	


	
}
