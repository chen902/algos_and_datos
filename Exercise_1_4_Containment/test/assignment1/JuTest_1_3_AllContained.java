package assignment1;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class JuTest_1_3_AllContained {

	private long[] m0 = {};
	private long[] m1 = {1,2,5};
	private long[] m2 = {2,5,1,5};
	private long[] m3 = {2,3,5};
	private long[] m4 = {1,5,7,2,9};
	
	private long[] a1;
	private long[] a2;
	
	public static final int LEN = 1000000;
	
	@Before
	public void setup() {
		a1 = new long[LEN];
		a2 = new long[LEN];
		
		for (int i = 0; i < LEN; i++) {
			a1[i] = i;
			a2[LEN - 1 - i] = i;
		}
	}
	
	@Test
	public void testTeilmenge0() {
		assertTrue(Exercise_1_4_Containment.allContained(m0, m0));
		assertTrue(Exercise_1_4_Containment.allContained(m0, m1));
		assertTrue(Exercise_1_4_Containment.allContained(m0, m2));
		assertTrue(Exercise_1_4_Containment.allContained(m0, m3));
		assertTrue(Exercise_1_4_Containment.allContained(m0, m4));
	}

	@Test
	public void testTeilmenge1() {
		assertFalse(Exercise_1_4_Containment.allContained(m1, m0));
		assertTrue(Exercise_1_4_Containment.allContained(m1, m1));
		assertTrue(Exercise_1_4_Containment.allContained(m1, m2));
		assertFalse(Exercise_1_4_Containment.allContained(m1, m3));
		assertTrue(Exercise_1_4_Containment.allContained(m1, m4));
	}
	
	@Test
	public void testTeilmenge2() {
		assertFalse(Exercise_1_4_Containment.allContained(m2, m0));
		assertTrue(Exercise_1_4_Containment.allContained(m2, m1));
		assertTrue(Exercise_1_4_Containment.allContained(m2, m2));
		assertFalse(Exercise_1_4_Containment.allContained(m2, m3));
		assertTrue(Exercise_1_4_Containment.allContained(m2, m4));
	}

	@Test
	public void testTeilmenge3() {
		assertFalse(Exercise_1_4_Containment.allContained(m3, m0));
		assertFalse(Exercise_1_4_Containment.allContained(m3, m1));
		assertFalse(Exercise_1_4_Containment.allContained(m3, m2));
		assertTrue(Exercise_1_4_Containment.allContained(m3, m3));
		assertFalse(Exercise_1_4_Containment.allContained(m3, m4));
	}

	@Test
	public void testTeilmenge4() {
		assertFalse(Exercise_1_4_Containment.allContained(m4, m0));
		assertFalse(Exercise_1_4_Containment.allContained(m4, m1));
		assertFalse(Exercise_1_4_Containment.allContained(m4, m2));
		assertFalse(Exercise_1_4_Containment.allContained(m4, m3));
		assertTrue(Exercise_1_4_Containment.allContained(m4, m4));
	}

	@Test
	public void testTeilmenge5fast() {
		assertTrue(Exercise_1_4_Containment.allContained(a1, a2));
		assertTrue(Exercise_1_4_Containment.allContained(a2, a1));
	}		
	
	@Test
	public void testTeilmenge5slow() {		
		assertTrue(Exercise_1_4_Containment.allContained(a1, a2));
		assertTrue(Exercise_1_4_Containment.allContained(a2, a1));
	}

}
