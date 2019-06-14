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

	public void addBeads(int beads) {
		this.numberOfBeads += beads;
	}
	
	public void setStartingNumberOfBeads(int beads) {
		this.numberOfBeads = beads;
	}

	public void emptyOwnBowl() {
		this.numberOfBeads = 0;
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
		else if (positionOfNeighbour < 0) {
			throw new IllegalArgumentException("Cannot get neighbour of a "
												+ "negative position");
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
	 * Gets the opposite bowl by counting your distance to your Kalaha
	 * and adding the distance to your kalaha to get the opposite cell.
	 * 
	 * @return	The opposite cell of your cell
	 */
	public Cell getOppositeCell() {
		return this.getNeighbour(2 * this.getDistanceToMyKalaha());
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
	

	abstract void doMove();
	abstract void distributeBeads(int beadsToDistribute);
	abstract Kalaha getMyKalaha();
	abstract int getDistanceToMyKalaha();
	abstract boolean areMyBowlsEmpty();
}
