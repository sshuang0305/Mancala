package nl.sogyo.mancala.domain;

abstract class Cell {

	protected int numberOfBeads = 4;
	protected int sizeOfDomain = 0;
	protected Player owner;

	protected Cell nextNeighbour;


	public int getNumberOfBeads() {
		return this.numberOfBeads;
	}

	public Cell getNextNeighbour() {
		return this.nextNeighbour;
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
	
	public Player getOwner() {
		return this.owner;
	}

	public void doMove() {
		this.distributeBeads(this.nextNeighbour, this.numberOfBeads);
		this.numberOfBeads = 0;
	}
	
	public void distributeBeads(Cell cell, int beadsToDistribute) {

		cell.numberOfBeads++;

		if (beadsToDistribute == 1) {
			
			// dit deel moet nog aangepast worden
			if (!(cell instanceof Kalaha) && cell.getOwner() != this.getOwner()) {
				cell.owner.switchTurnBothPlayers();
			}

		}
		else {
			distributeBeads(cell.nextNeighbour, beadsToDistribute - 1);
		}

	}

}
