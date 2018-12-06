package assignment4.exercise4_5_priolist;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class JuTestLinkedPrioQueue {

	private LinkedPrioQueue<String> prioQueue;
	public static int ANZAHL = 100;
	
	@Before
	public void setup() {
		prioQueue = new LinkedPrioQueue<String>();
	}
	
	@Test
	public void test01EmptyFull() {
		//Priorit�tenwarteschlange am Anfang leer
		assertTrue(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());

		//Eintr�ge einf�gen ==> nicht mehr leer
		prioQueue.insert(new Entry<String>("Xaver", 4));
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());

		//noch einen Eintr�ge einf�gen ==> nicht leer
		prioQueue.insert(new Entry<String>("Yvonne", 3));
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());
		
		//einen Eintr�ge entfernen ==> noch nicht leer
		prioQueue.extractMin();
		assertFalse(prioQueue.isEmpty());
		assertFalse(prioQueue.isFull());
		
		//noch einen Eintr�ge entfernen ==> jetzt wieder leer
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
		
		//einige Werte eintragen
		prioQueue.insert(e3);
		prioQueue.insert(e2a);
		prioQueue.insert(e5);
		prioQueue.insert(e1a);
		prioQueue.insert(e2b);
		
		//Inhalt jetzt: {e1a, e2a, e2b,e3, e5}
		
		//Wert entfernen, sollte "E1a" mit Priorit�t 1 liefern
		Entry<String> emin = prioQueue.extractMin();
		assertEquals("E1a", emin.getValue());
		assertEquals(1, emin.getPriority());
		
		//Inhalt jetzt: {e2a, e2b,e3, e5}
		
		//Wert entfernen, sollte "E2a" oder "E2b" mit Priorit�t 2 liefern
		emin = prioQueue.extractMin();
		assertEquals(2, emin.getPriority());
				
		//Inhalt jetzt: {e2b,e3, e5} oder {e2a, e3, e5}
		
		//Wert entfernen, sollte Wert mit Priorit�t 2 liefern ( "E2b" oder "E2a" )
		emin = prioQueue.extractMin();
		assertEquals(2, emin.getPriority());
		
		//Inhalt jetzt: {e3, e5} 
		
		//Werte einf�gen
		prioQueue.insert(e4);
		prioQueue.insert(e1b);
		
		//Inhalt jetzt: {e1b, e3, e4, e5}
		
		//Wert entfernen, sollte "E1b" mit Priorit�t 1 liefern
		emin = prioQueue.extractMin();
		assertEquals("E1b", emin.getValue());
		assertEquals(1, emin.getPriority());
		
		//Wert entfernen, sollte Priorit�t 3 haben
		emin = prioQueue.extractMin();
		assertEquals("E3", emin.getValue());
		assertEquals(3, emin.getPriority());
		
		//Inhalt jetzt: {e4, e5}
		
		//Werte einf�gen
		prioQueue.insert(e6);

		//Inhalt jetzt: {e4, e5, e6}

		
		//Wert entfernen, sollte Priorit�t 4 haben
		emin = prioQueue.extractMin();
		assertEquals("E4", emin.getValue());
		assertEquals(4, emin.getPriority());

		//Wert entfernen, sollte Priorit�t 5 haben
		emin = prioQueue.extractMin();
		assertEquals("E5", emin.getValue());
		assertEquals(5, emin.getPriority());


		//Wert entfernen, sollte Priorit�t 6 haben
		emin = prioQueue.extractMin();
		assertEquals("E6", emin.getValue());
		assertEquals(6, emin.getPriority());

		//Warteschlange sollte jetzt leer sein
		assertTrue(prioQueue.isEmpty());	
	}
	
	@Test
	public void test03() {

		//10 Werte mit den Priorit�ten 20, 18, ..., 4, 2 einf�gen
		for (int i = 20; i > 0; i -= 2) {
			Entry<String> entry = new Entry<String>("W" + i, i);
			prioQueue.insert(entry);
			System.out.println("insert: " + entry);
		}

		
		//10 Werte mit den Priorit�ten 1,3,5, ..., 19 einf�gen
		for (int i = 1; i <= 20; i += 2) {
			Entry<String> entry = new Entry<String>("W" + i, i);
			prioQueue.insert(entry);
			System.out.println("insert: " + entry);
		}
		
		
		//20 Werte aus der Priorit�tenwarteschlange entfernen
		//sollte die Werte W1, W2, W3, ...., W20 liefern
		for (int k = 1; k <= 20; k++) {
			Entry<String> entry = prioQueue.extractMin();
			System.out.println("remove: " + entry);
			assertEquals(k, entry.getPriority());
			assertEquals("W"+k, entry.getValue());
		}
		
		assertTrue(prioQueue.isEmpty());
	}
	
	@Test
	public void test04Random() {
		//zuf�llig gew�hlte Eintr�ge in der Warteschlange ablegen 
		for (int i = 0; i < ANZAHL; i++) {
			Entry<String> neu = randEntry(1000);
			prioQueue.insert(neu);
		}		

		// Alle Eintr�ge entfernen, m�ssen nach Priorit�t geordnet sein
		Entry<String> current = prioQueue.extractMin();
		Entry<String> previous;
		int count = 1;
		while (! prioQueue.isEmpty()) {
			previous = current;
			Entry<String> next = prioQueue.extractMin();
			assertTrue(next.getPriority() >= previous.getPriority());
			count++;
		}
		assertEquals(ANZAHL, count);
	
	}

	public static Random rand = new Random();
	
	public static Entry<String> randEntry(int maxValue) {
		int prio = rand.nextInt(maxValue); 
		return new Entry<String>("V"+prio, prio);
	}
	
}
