package nl.sogyo.mancala.domain;

abstract class Cell {

	protected int numberOfBeads = 4;
	protected int sizeOfDomain = 0;
	protected Player owner;

	protected Cell nextNeighbour;
	final static int numberOfCells = 14;


	public int getNumberOfBeads() {
		return this.numberOfBeads;
	}

	public Cell getNextNeighbour() {
		return this.nextNeighbour;
	}
	
	public Cell getANeighbour(int number) {
		
		if (number == 0) {
			return this;
		}

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
		
		if (this.numberOfBeads == 0) {
			throw new RuntimeException("Bowl does not contain beads.");
		}
		
		if (!this.owner.getMyTurn()) {
			throw new RuntimeException("Its not your turn.");
		}
		
		int beadsToDistribute = this.numberOfBeads;
		this.numberOfBeads = 0;
		this.distributeBeads(this.nextNeighbour, beadsToDistribute);

		this.owner.checkIfGameFinished();

	}
	

	
	public void distributeBeads(Cell cell, int beadsToDistribute) {


		if (beadsToDistribute == 1) {


			cell.numberOfBeads++;
			
			if (cell.getNumberOfBeads() == 1) {
				cell.stealBeadsOppositeCell();
			}

			if (cell instanceof Kalaha && !cell.getOwner().getMyTurn() || !(cell instanceof Kalaha)) {
				this.owner.switchTurnBothPlayers();
				
			}


		}

		else {
			if (cell instanceof Kalaha && !cell.getOwner().getMyTurn()) {
				distributeBeads(cell.nextNeighbour, beadsToDistribute);
			}
			else {
				cell.numberOfBeads++;
				distributeBeads(cell.nextNeighbour, beadsToDistribute - 1);
			}
		}

	}
	
	public Cell getOppositeCell() {
		
		if (this instanceof Kalaha) {
			return this.getANeighbour(numberOfCells / 2);
		}
		
		int counter = 0;
		Cell next = this.getNextNeighbour();
		
		while (!(next instanceof Kalaha)) {
			next = next.getNextNeighbour();
			counter++;
		}
		
		if (next instanceof Kalaha) {
			Cell oppositeCell = next.getANeighbour(counter + 1);
			return oppositeCell;
		}
		return next;
	}
	
	public void emptyBowl() {
		this.numberOfBeads = 0;
	}
	
	public void stealBeadsOppositeCell() {
		
		this.numberOfBeads += this.getOppositeCell().numberOfBeads;
		this.getOppositeCell().numberOfBeads = 0;


		Cell nextCell = this.getNextNeighbour();
		
		while (!(nextCell instanceof Kalaha)) {
			nextCell = nextCell.getNextNeighbour();
		}

		nextCell.numberOfBeads += this.numberOfBeads;
		this.numberOfBeads = 0;
		this.getOppositeCell().numberOfBeads = 0;
	}

}
