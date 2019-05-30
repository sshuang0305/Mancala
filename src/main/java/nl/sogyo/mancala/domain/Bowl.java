/**
 * Bowl.java
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

class Bowl extends Cell {

	final static int STARTING_NO_BEADS = 4;
	
	/**
	 * Bowl constructor to create the first bowl.
	 * Initializes the number of beads, assigns an owner to the bowl,
	 * and creates 13 neighbouring bowls.
	 */
	public Bowl() {
		this.numberOfBeads = STARTING_NO_BEADS;
		this.owner = new Player();
		this.nextNeighbour = new Bowl(TOTAL_NO_CELLS - 1, this, owner);

	}
	
	/**
	 * Bowl constructor to create neighbour cells.
	 * Initializes the number of beads, assigns the owner of the cell,
	 * and assures that the neighbour of last cell is the first bowl again.
	 * @param counter		Integer to count how many cells to make
	 * @param firstBowl		Reference to firstbowl for the last cell to point to
	 * @param owner			Assignes an owner to each bowl/kalaha (each owner has 6bowls + 1kalaha)
	 */
	Bowl(int counter, Bowl firstBowl, Player owner) {

		this.numberOfBeads = STARTING_NO_BEADS;		
		if (counter < 8) {
			this.owner = owner.opponent;
		}
		else {
			this.owner = owner;
		}

		if(counter == 2 || counter == (TOTAL_NO_CELLS / 2 + 2)) {
			this.nextNeighbour = new Kalaha(counter - 1, firstBowl, owner);
			this.owner.setKalaha(this.nextNeighbour);
		}
		else {
			this.nextNeighbour = new Bowl(counter - 1, firstBowl, owner);
		}
	}
}
