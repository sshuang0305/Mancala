/**
 * Kalaha.java
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

class Kalaha extends Cell {

	final static int STARTING_NO_BEADS = 0;
	final static int TOTAL_NO_BEADS = 12 * 4;

	/**
	 * Kalaha constructor.
	 * Initializes the number of beads, assigns the owner to a kalaha.
	 * @param counter		Integer that specifies the position for the kalaha
	 * @param firstBowl		Reference to firstbowl for the last cell to point to
	 * @param owner			Assignes an owner to each kalaha
	 */
	public Kalaha(int counter, Bowl firstBowl, Player owner) {
		super.setStartingNumberOfBeads(0);
		if (counter == 8) {
			super.setStartingOwner(owner);
			super.setNextNeighbour(new Bowl(counter - 1, firstBowl, owner));
		}
		else {
			super.setStartingOwner(owner.getOpponent());
			super.setNextNeighbour(firstBowl);
		}
	}
	
	/**
	* Overrides the doMove function.
	*
	* @throws RuntimeException If move is called on a kalaha
	*/
	public void doMove() {
		throw new RuntimeException("Cannot do a move on a kalaha.");
	}
	
	/**
	* Overrides the steal function.
	*
	* @throws RuntimeException If steal is called on a kalaha
	*/
	public void steal() {
		throw new RuntimeException("Cannot steal from kalaha.");
	}
	
	/**
	 * When called on a cell, it will return the score of its owner.
	 * @return The total number of beads in own kalaha and own 6 bowls
	 */
	public int getScore() {
		
		Cell nextCell = this.getNextNeighbour();
		int numberOfBeadsOpponent = this.getOwner().getOpponent().getKalaha().getNumberOfBeads();
		
		while (!(nextCell instanceof Kalaha)) {
			numberOfBeadsOpponent += nextCell.getNumberOfBeads();
			nextCell = nextCell.getNextNeighbour();
		}
		return  TOTAL_NO_BEADS- numberOfBeadsOpponent;
	}

	@Override
	Kalaha getMyKalaha() {
		return this;
	}

	@Override
	int getDistanceToMyKalaha() {
		return 0;
	}
}
