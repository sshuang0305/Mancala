package nl.sogyo.mancala.domain;

class Kalaha extends Cell {
	
	final static int numberOfCells = 14;
	
	
	public Kalaha(int n, Bowl origin) {
		this.startingNumberOfBeads = 0;
		this.nextNeighbour = new Bowl(n - 1, origin);
	}

}
