/**
 * Kalaha.java
 * 
 * @author Shan Shan Huang
 * @since 22-05-19
 */

package nl.sogyo.mancala.domain;

class Kalaha extends Cell {

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
	*/
	public void doMove() {
		System.out.println("Cannot do move on kalaha. Try again");
	}

	/**
	 * Overrides the distributeBeads function.
	 * 
	 * Distribute beads by adding a bead to own kalaha if it's own kalaha,
	 * will not add bead if kalaha not yours. Switches players if bead does 
	 * not end in own kalaha.
	 */
	public void distributeBeads(int beadsToDistribute) {
		
		if (beadsToDistribute == 0) {
			if (!this.getOwner().getMyTurn()) {
				this.getOwner().switchTurnBothPlayers();
			}
		}
		else {
			if (this.getOwner().getMyTurn()) {
				this.addBeads(1);
				this.getNextNeighbour().distributeBeads(beadsToDistribute - 1);
			}
			else {
				this.getNextNeighbour().distributeBeads(beadsToDistribute);
			}
		}
	}
	
	/**
	* Overrides the steal function.
	*
	* @throws RuntimeException If steal is called on a kalaha
	*/
	public void steal() {
		throw new RuntimeException("Cannot steal from kalaha.");
	}

	@Override
	Kalaha getMyKalaha() {
		return this;
	}

	@Override
	int getDistanceToMyKalaha() {
		return 0;
	}
	
	@Override
	public Cell getOppositeCell() {
		return this.getNeighbour(7);
	}

	/**
	 * Overrides the areYouEmpty function.
	 * Checks whether neighbouring bowl is empty until the kalaha.
	 * 
	 * @return boolean true if all bowls until kalaha are empty, false otherwise
	 */
	boolean areYouEmpty() {
		return true;
	}

	/**
	 * Overrides the addYourBeadsToMyScore function.
	 * 
	 * @return your score
	 */
	int addYourBeadsToMyScore(int score) {
		return this.getNumberOfBeads() + score;
	}
}
