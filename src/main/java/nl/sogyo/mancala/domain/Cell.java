/**
 * Cell.java
 *
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

abstract class Cell {

	final static int TOTAL_NO_CELLS = 14;

	private int numberOfBeads;
	private int sizeOfDomain = 0;
	private Player owner;
	private Cell nextNeighbour;
	
	public int getNumberOfBeads() {
		return this.numberOfBeads;
	}
	
	public void addOneBead() {
		this.numberOfBeads++;
	}
	
	public void addBeads(int beads) {
		this.numberOfBeads += beads;
	}
	
	public void setStartingNumberOfBeads(int beads) {
		this.numberOfBeads = beads;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public void setStartingOwner(Player myOwner) {
		this.owner = myOwner;
	}
	
	public Cell getNextNeighbour() {
		return this.nextNeighbour;
	}
	
	public void setNextNeighbour(Cell neighbour) {
		this.nextNeighbour = neighbour;
	}
	
	/**
	 * Gets your neighbour (Bowl/Kalaha) at a specified position
	 *
	 * @param positionOfNeighbour Integer to specify position of neighbour
	 * @return					  Neighbour at specified position
	 */
	public Cell getNeighbour(int positionOfNeighbour) {

		if (positionOfNeighbour == 0) {
			return this;
		}
		Cell neighbour = this.nextNeighbour;
		for (int i = 1; i < positionOfNeighbour; i++) {
			neighbour = neighbour.nextNeighbour;
		}
		return neighbour;
	}
	
	/**
	 * Gets the total amount of neighbours your cell has.
	 * @return Integer with domainsize
	 */
	public int getSizeOfDomain() {

		Cell nextCell = this.nextNeighbour;		
		while (!(nextCell.equals(this))) {
			this.sizeOfDomain ++;
			nextCell = nextCell.nextNeighbour;
		}
		return sizeOfDomain + 1;
	}

	/**
	 * When a move is called on a cell, it will check if the move is valid.
	 * If valid it will distribute its beads to the neighbours.
	 * After each move, there will be a check too see if game is finished.
	 *
	 * @throws RuntimeException If move is invalid
	 */
	public void doMove() {

		boolean bowlEmpty = this.numberOfBeads == 0;
		int beadsToDistribute = this.numberOfBeads;

		if (!bowlEmpty && this.owner.getMyTurn()) {
			this.distributeBeads(beadsToDistribute);
			this.emptyOwnBowl();
			this.checkIfGameFinished();
		}
	}

	/**
	 * Distributes beads that it receives by adding one bead to own cell and passing
	 * the remaining beads to the neighbour.
	 * When passing the kalaha of the opponent, it will not add a bead to it.
	 * When the last bead ends in bowl on own side, it can steal beads of the opposite cell.
	 * When the last bead ends in own kalaha, the player gets to play again.
	 * 
	 * @param nextCell 				The cell you want to pass your beads to
	 * @param beadsToDistribute		Number of beads you want to distribute
	 */
	public void distributeBeads(int beadsToDistribute) {}
	
	/**
	 * Gets the opposite bowl by counting your distance to your Kalaha
	 * and adding the distance to your kalaha to get the opposite cell.
	 * 
	 * @return	The opposite cell of your cell
	 */
	public Cell getOppositeCell() {
		Cell oppositeCell = this.getNeighbour(2 * this.getDistanceToMyKalaha());
		return oppositeCell;
	}


	
	/**
	 * When called on a bowl, it will set the number of beads in the bowl to 0.
	 */
	public void emptyOwnBowl() {
		this.numberOfBeads = 0;
	}
	
	
	public boolean areAllMyBowlsEmpty() {
		Cell opponnentsKalaha = this.getMyKalaha().getOppositeCell();
		return opponnentsKalaha.getNextNeighbour().areMyBowlsEmpty();
	
	}
	
	public void checkIfGameFinished() {
		if (this.areAllMyBowlsEmpty() || this.getOppositeCell().areAllMyBowlsEmpty()) {
			this.getOwner().gameFinished = true;
			this.getOwner().getOpponent().gameFinished = true;
		}
	}
	
	abstract Kalaha getMyKalaha();
	abstract int getDistanceToMyKalaha();
	abstract boolean areMyBowlsEmpty();
}
