package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KalahaTest {
	
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

		Cell firstKalaha = cellNumber1.getNextNeighbour();
		Cell secondKalaha = cellNumber1.getANeighbour(8);
		assertEquals(0, firstKalaha.getNumberOfBeads());
		assertEquals(0, secondKalaha.getNumberOfBeads());
	}
	

}
