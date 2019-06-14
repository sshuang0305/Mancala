/**
 * Bowl.java
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

class Bowl extends Cell {

	/**
	 * Bowl constructor to create the first bowl.
	 * Initializes the number of beads, assigns an owner to the bowl,
	 * and creates 13 neighbouring bowls.
	 */
	public Bowl() {
		super.setStartingNumberOfBeads(4);
		super.setStartingOwner(new Player());
		super.setNextNeighbour(new Bowl(TOTAL_NO_CELLS - 1, this, getOwner()));

	}
	
	/**
	 * Bowl constructor to create neighbour cells.
	 * Initializes the number of beads, assigns the owner of the cell,
	 * and assures that the neighbour of last cell is the first bowl again.
	 * @param counter		Integer to count how many cells to make
	 * @param firstBowl		Reference to first bowl for the last cell to point to
	 * @param owner			Assigns an owner to each bowl/kalaha (each owner has 6bowls + 1kalaha)
	 */
	Bowl(int counter, Bowl firstBowl, Player owner) {
		super.setStartingNumberOfBeads(4);
		if (counter < 8) {
			super.setStartingOwner(owner.getOpponent());
		}
		else {
			super.setStartingOwner(owner);
		}

		if(counter == 2 || counter == (TOTAL_NO_CELLS / 2 + 2)) {
			super.setNextNeighbour(new Kalaha(counter - 1, firstBowl, owner));
		}
		else {
			super.setNextNeighbour(new Bowl(counter - 1, firstBowl, owner));
		}
	}

	@Override
	Kalaha getMyKalaha() {
		return this.getNextNeighbour().getMyKalaha();
	}

	@Override
	int getDistanceToMyKalaha() {
		return this.getNextNeighbour().getDistanceToMyKalaha() + 1;
	}
	
	@Override
	public void doMove() {

		boolean bowlEmpty = this.getNumberOfBeads() == 0;
		int beadsToDistribute = this.getNumberOfBeads();

		if (!bowlEmpty && this.getOwner().getMyTurn()) {
			this.distributeBeads(beadsToDistribute);
			this.emptyOwnBowl();
			this.checkIfGameFinished();
		}
	}
	
	@Override
	public void distributeBeads(int beadsToDistribute) {
		
		boolean beadEndsInOwnEmptyBowl = this.getNumberOfBeads() == 0 &&
										 this.getOwner().getMyTurn();

		if (beadsToDistribute == 0) {
			this.addBeads(1);
			if (beadEndsInOwnEmptyBowl) {
				this.stealBeadsOppositeCell();
			}
			this.getOwner().switchTurnBothPlayers();

		}
		else {
			this.addBeads(1);
			this.getNextNeighbour().distributeBeads(beadsToDistribute - 1);
		}
	}
	
	/**
	 * Steals the beads from the opposite bowl by adding those beads to own bowl,
	 * and then adding all beads to your own kalaha, leaving both bowls empty.
	 */
	public void stealBeadsOppositeCell() {
		
		this.addBeads(this.getOppositeCell().getNumberOfBeads());
		this.getOppositeCell().emptyOwnBowl();

		Kalaha myKalaha = this.getMyKalaha();

		myKalaha.addBeads(this.getNumberOfBeads());
		this.emptyOwnBowl();
	}


	@Override
	boolean areMyBowlsEmpty() {

		if (this.getNumberOfBeads() == 0) {
			return this.getNextNeighbour().areMyBowlsEmpty();
		}
		return false;
	}
}
