package assignment1;


import static org.junit.Assert.*;


import org.junit.Test;

public class JuTest_1_7_ContainsCount {

	@Test
	public void testContains0() {
		double[] a0 = {};
		assertEquals(false, Exercise_1_7_ContainsCount.contains(3.0, a0, a0.length-1));
	}
		
	@Test
	public void testContains1() {
		double[] a1 = { 3.0 };

		assertEquals(true, Exercise_1_7_ContainsCount.contains(3.0, a1, a1.length-1));
		assertEquals(false, Exercise_1_7_ContainsCount.contains(4.0, a1, a1.length-1));
		
	}
		
	@Test
	public void testContains2() {
		
		double[] a2 = { 3.0, 4.0, 8.0 };
		assertEquals(true, Exercise_1_7_ContainsCount.contains(3.0, a2, a2.length-1));
		assertEquals(true, Exercise_1_7_ContainsCount.contains(4.0, a2, a2.length-1));
		assertEquals(false, Exercise_1_7_ContainsCount.contains(5.0, a2, a2.length-1));
		assertEquals(true, Exercise_1_7_ContainsCount.contains(8.0, a2, a2.length-1));
	}


	
	@Test
	public void testCount1() {
		char[] c1 = {'a'};
		
		assertEquals(1, Exercise_1_7_ContainsCount.count('a', c1));
		assertEquals(0, Exercise_1_7_ContainsCount.count('b', c1));
	}
	
	@Test
	public void testCount2() {
		char[] c1 = {'a', 'b', 'b', 'a', 'c', 'a',  'd' };
		
		assertEquals(3, Exercise_1_7_ContainsCount.count('a', c1));
		assertEquals(2, Exercise_1_7_ContainsCount.count('b', c1));
		assertEquals(1, Exercise_1_7_ContainsCount.count('c', c1));
		assertEquals(1, Exercise_1_7_ContainsCount.count('d', c1));
		assertEquals(0, Exercise_1_7_ContainsCount.count('x', c1));


	}

	
}
