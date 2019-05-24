package nl.sogyo.mancala.domain;

abstract class Cell {

	protected int startingNumberOfBeads = 4;
	protected int sizeOfDomain = 0;

	Cell nextNeighbour;


	public int getNumberOfBeads() {
		return startingNumberOfBeads;
	}

	public Cell getNextNeighbour() {
		return nextNeighbour;
	}
	
	public Cell getANeighbour(int number) {

		Cell aNeighbour = nextNeighbour;
		
		for (int i = 1; i < number; i++) {
			aNeighbour = aNeighbour.nextNeighbour;
		}
		return aNeighbour;
	}
	
	public int getSizeOfDomain(Cell original) {
		
		if (this.getNextNeighbour().equals(original)) {
			return original.sizeOfDomain + 1;
		}
		
		else if (this.getNextNeighbour() != null) {

			original.sizeOfDomain++;
			return this.getNextNeighbour().getSizeOfDomain(original);
		}
		
		return sizeOfDomain;
	}

}
