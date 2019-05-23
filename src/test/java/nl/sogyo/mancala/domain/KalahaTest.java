package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class KalahaTest {
	
	Kalaha firstKalaha = new Kalaha();

	@Test
	public void getNumberofBeadsTest() {

		int beads = firstKalaha.getNumberOfBeads();
		assertEquals(0, beads);
	}

}
