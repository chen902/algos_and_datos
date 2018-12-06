package assignment4.exercise4_3_heapqueue;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class JuTestHeapQueue {

	private IPriorityQueue<String> prioQueue;
	public static int CAPACITY = 100;

	@Before
	public void setup() {
		prioQueue = new HeapQueue<String>(CAPACITY);
	}
	
	@Test
	public void test01EmptyFull() {
		// at the beginning, queue is empty
		assertTrue(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());

		// insert entry ==> queue is not empty
		prioQueue.insert(new Entry<String>("Xaver", 4));
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());

		// insert another entry
		prioQueue.insert(new Entry<String>("Yvonne", 3));
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());
		
		// extract an entry ==> not empty
		prioQueue.extractMin();
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());
		
		// extract another entry ==> now empty
		prioQueue.extractMin();
		assertTrue(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());
	}

	
	@Test
	public void test02insertExtractMin() {
		Entry<String> e1a = new Entry<String>("E1a", 1);
		Entry<String> e1b = new Entry<String>("E1b", 1);
		Entry<String> e2a = new Entry<String>("E2a", 2);
		Entry<String> e2b = new Entry<String>("E2b", 2);
		Entry<String> e3 = new Entry<String>("E3", 3);
		Entry<String> e4 = new Entry<String>("E4", 4);
		Entry<String> e5 = new Entry<String>("E5", 5);
		Entry<String> e6 = new Entry<String>("E6", 6);
		
		//insert some entries
		prioQueue.insert(e3);
		prioQueue.insert(e2a);
		prioQueue.insert(e5);
		prioQueue.insert(e1a);
		prioQueue.insert(e2b);
		
		//contents now: {E1a, E2a, E2b,E3, E5}
		
		//extract minimum: extracted element should have prio 1
		Entry<String> emin = prioQueue.extractMin();
		assertEquals(1, emin.getPriority());
		assertEquals("E1a", emin.getValue());
		
		//contents now: {E2a, E2b,E3, E5}
		
		//extract minimum: extracted element should have prio 2
		emin = prioQueue.extractMin();
		assertEquals(2, emin.getPriority());
				
		//contents now: {E2b,E3, E5} (or E2a instead of E2b)
		
		// insert two entries
		prioQueue.insert(e4);
		prioQueue.insert(e1b);
		
		//contents now: {E1b, E2b, E3, E4, E5}
		
		//extract minimum ==> prio 1 expected
		emin = prioQueue.extractMin();
		assertEquals(1, emin.getPriority());
		
		//extract minimum ==> prio 2 expected
		emin = prioQueue.extractMin();
		assertEquals(2, emin.getPriority());
		
		//extract minimum ==> prio 3 expected
		emin = prioQueue.extractMin();
		assertEquals(3, emin.getPriority());
		
		//contents now: {E4, E5}
		
		//insert another value
		prioQueue.insert(e6);

		//contents now: {E4, E5, E6}

		//extract minimum ==> prio 4 expected
		emin = prioQueue.extractMin();
		assertEquals(4, emin.getPriority());

		//extract minimum ==> prio 4 expected
		emin = prioQueue.extractMin();
		assertEquals(5, emin.getPriority());

		//extract minimum ==> prio 6 expected
		emin = prioQueue.extractMin();
		assertEquals(6, emin.getPriority());

		// queue should be empty now
		assertTrue(prioQueue.isEmpty());	
	}
	
	
	@Test
	public void test03Random() {
		// fill queue completely with random entries
		for (int i = 0; i < CAPACITY; i++) {
			Entry<String> neu = randEintrag(1000);
			prioQueue.insert(neu);
		}		

		// queue should be full now
		assertTrue(prioQueue.isFull());
		
		
		// extract all entries ==> should be sorted 
		Entry<String> current = prioQueue.extractMin();
		Entry<String> previous;
		int count = 1;
		while (! prioQueue.isEmpty()) {
			previous = current;
			Entry<String> next = prioQueue.extractMin();
			assertTrue(next.getPriority() >= previous.getPriority());
			count++;
		}
		// check that all entries have been removed
		assertEquals(CAPACITY, count);
	
	}

	public static Random rand = new Random();
	
	/** generate a random entry
	 * 
	 * @param maxValue upper bound for value and priority
	 * @return	entry with random priority and value
	 */
	public static Entry<String> randEintrag(int maxValue) {
		int prio = rand.nextInt(maxValue); 
		return new Entry<String>("V"+prio, prio);
	}
	
}
