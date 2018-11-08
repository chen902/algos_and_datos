package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class JuTestRowReorder {

	@Test
	public void test1() {
		PersonRow m1 = new PersonRow(100);
		m1.add(new Person(AgeGroup.ADULT, "Anna"));
		m1.add(new Person(AgeGroup.CHILD, "Berta"));
		m1.add(new Person(AgeGroup.TEENAGER, "Caesar"));
		m1.add(new Person(AgeGroup.ADULT, "Dora"));
		m1.add(new Person(AgeGroup.CHILD, "Emil"));

		RowTest.reorder(m1);

		assertTrue(m1.isOrdered());
	}
	
	@Test
	public void test2() {
		PersonRow m2 = PersonRow.generateRandomRow(50);
		RowTest.reorder(m2);
		assertTrue(m2.isOrdered());
	}
	
	@Test
	public void test3() {
		PersonRow m2 = PersonRow.generateRandomRow(1000000);
		RowTest.reorder(m2);
		assertTrue(m2.isOrdered());
	}

	
}
