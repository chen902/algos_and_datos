package assignment1;

import static org.junit.Assert.*;
import java.util.Set;

import org.junit.Test;

public class  JuTest_1_8_Strings{

	@Test
	public void testLength0() {
		char[] chrs = {'A', 'B', 'C' };
		Set<String> sequences = Exercise_1_8_Strings.generateStrings(chrs, 0);
		assertEquals(1, sequences.size());
		assertTrue(sequences.contains(""));
	}

	@Test
	public void testLength1() {
		char[] chrs = {'A', 'B', 'C' };
		Set<String> sequences = Exercise_1_8_Strings.generateStrings(chrs, 1);
		assertEquals(3, sequences.size());
		assertTrue(sequences.contains("A"));
		assertTrue(sequences.contains("B"));
		assertTrue(sequences.contains("C"));
	}


	@Test
	public void testLength2() {
		char[] chrs = {'A', 'B', 'C' };
		Set<String> sequences = Exercise_1_8_Strings.generateStrings(chrs, 2);
		assertEquals(9, sequences.size());
		assertTrue(sequences.contains("AA"));
		assertTrue(sequences.contains("AB"));
		assertTrue(sequences.contains("AC"));
		assertTrue(sequences.contains("BA"));
		assertTrue(sequences.contains("BB"));
		assertTrue(sequences.contains("BC"));
		assertTrue(sequences.contains("CA"));
		assertTrue(sequences.contains("CB"));
		assertTrue(sequences.contains("CC"));
	}

	@Test
	public void testLength3() {
		char[] chrs = {'A', 'B', 'C', 'D' };
		Set<String> sequences = Exercise_1_8_Strings.generateStrings(chrs, 3);
		assertEquals(4*4*4, sequences.size());
		
		assertTrue(sequences.contains("BBB"));
		assertTrue(sequences.contains("ABC"));
		assertTrue(sequences.contains("BCD"));
		assertTrue(sequences.contains("CBA"));
		assertTrue(sequences.contains("DCB"));
		assertTrue(sequences.contains("BDA"));
	}

	
}
