package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlTest {
	
	Bowl cellNumber1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cellNumber1 = new Bowl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cellNumber1 = null;
	}
	

	@Test
	public void getNumberofBeadsTest() {

		int beads = cellNumber1.getNumberOfBeads();
		assertEquals(4, beads);
	}
	
	@Test
	public void getfourteenCellsTest() {
		int domainSize = cellNumber1.getSizeOfDomain(cellNumber1);
		assertEquals(14, domainSize);
	}
	

}
