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

		boolean bowlEmpty = this.numberOfBeads == 0 ? true : false;

		if (!bowlEmpty && this.owner.getMyTurn()) {
			this.distributeBeads(this.nextNeighbour, this.numberOfBeads);
			this.emptyOwnBowl();
			this.owner.checkIfGameFinished();
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
	public void distributeBeads(Cell nextCell, int beadsToDistribute) {
		
		boolean beadEndsInOwnEmptyBowl = nextCell.numberOfBeads == 0 &&
										 nextCell.owner.getMyTurn();
	
		// VERANDEREN: dit is gedrag van kalaha!
		boolean beadNotEndingInOwnKalaha = !(nextCell instanceof Kalaha) ||
										   (nextCell instanceof Kalaha &&
										   !nextCell.owner.getMyTurn());

		if (beadsToDistribute == 1) {
			nextCell.numberOfBeads++;
			if (beadEndsInOwnEmptyBowl) {
				nextCell.stealBeadsOppositeCell();
			}
			if (beadNotEndingInOwnKalaha) {
				this.owner.switchTurnBothPlayers();
			}
		}

		else {
			// VERANDEREN: dit is gedrag van kalaha!
			if (nextCell instanceof Kalaha && !nextCell.owner.getMyTurn()) {
				distributeBeads(nextCell.nextNeighbour, beadsToDistribute);
			}
			nextCell.numberOfBeads++;
			distributeBeads(nextCell.nextNeighbour, beadsToDistribute - 1);
		}
	}
	
	/**
	 * Gets the opposite bowl by counting your distance to your Kalaha
	 * and adding the distance to your kalaha to get the opposite cell.
	 * 
	 * @return	The opposite cell of your cell
	 */
	public Cell getOppositeBowl() {
		Cell oppositeCell = this.getNeighbour(2 * this.getDistanceToMyKalaha());
		return oppositeCell;
	}

	/**
	 * Steals the beads from the opposite bowl by adding those beads to own bowl,
	 * and then adding all beads to your own kalaha, leaving both bowls empty.
	 */
	public void stealBeadsOppositeCell() {
		
		this.numberOfBeads += this.getOppositeBowl().numberOfBeads;
		this.getOppositeBowl().emptyOwnBowl();

		Cell nextCell = this.nextNeighbour;
		while (!(nextCell instanceof Kalaha)) {
			nextCell = nextCell.nextNeighbour;
		}
		Cell myKalaha = nextCell;
		myKalaha.numberOfBeads += this.numberOfBeads;
		this.emptyOwnBowl();
	}
	
	/**
	 * When called on a bowl, it will set the number of beads in the bowl to 0.
	 */
	public void emptyOwnBowl() {
		this.numberOfBeads = 0;
	}
	
	abstract Kalaha getMyKalaha();
	abstract int getDistanceToMyKalaha();
}
