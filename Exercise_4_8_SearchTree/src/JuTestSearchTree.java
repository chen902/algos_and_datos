/** JUnit testcases for class SearchTree
 * 
 */

import org.junit.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class JuTestSearchTree {
	//beide Felder enthalten die gleichen Werte 1 bis 10, allerdings in
	//unterschiedlicher Reihenfolge
	public final static int[] werte1 = {4,2,1,7,5,8,9,3,10,6};
	public final static int[] werte2 = {9,10,8,6,4,2,1,7,5,3};
	
	/** sum-Methode f�r B�ume pr�fen, Reihenfolge 1 */
	@Test
	public void testSum1() {
		//Suchbaum erzeugen
		SearchTree tree1 = new SearchTree();

		//Leere Baum liefert Summe 0
		assertEquals(0, tree1.sum());

		//Werte 1 bis 10 eintragen (in unterschiedlicher Reihenfolge)
		for (int n : werte1) {
			tree1.insert(n);
		}
						
		//Summe der Werte von 1 bis 10 ist 55
		assertEquals(55, tree1.sum());
		
		//Wert 3 einf�gen (war schon enthalten)
		assertEquals(false, tree1.insert(3));	
		
		//Wert 42 einf�gen (bisher noch nicht enthalten)
		assertEquals(true,  tree1.insert(42));
		assertEquals(97, tree1.sum());
	}

	/** sum-Methode f�r B�ume pr�fen,  Reihenfolge 2  */
	@Test
	public void testSum2() {
		//Zwei Suchb�ume erzeugen
		SearchTree tree2 = new SearchTree();

		//Leere Baum liefert Summe 0
		assertEquals(0, tree2.sum());

		//Werte 1 bis 10 eintragen (in unterschiedlicher Reihenfolge)
		for (int n : werte2) {
			tree2.insert(n);
		}
						
		//Summe der Werte von 1 bis 10 ist 55
		assertEquals(55, tree2.sum());
		
		//Wert 3 einf�gen (war schon enthalten)
		assertEquals(false, tree2.insert(3));	
		
		//Wert 42 einf�gen (bisher noch nicht enthalten)
		assertEquals(true,  tree2.insert(42));
		assertEquals(97, tree2.sum());
	}

	
	/** numOfLeaves-Methode f�r B�ume pr�fen */
	@Test
	public void testNumOfLeaves1() {
		SearchTree t1 = new SearchTree();

		//leere Baum hat 0 Bl�tter
		assertEquals(0, t1.numOfLeaves());
		
		//Baum mit einem Knoten hat 1 Blatt
		t1.insert(1);
		assertEquals(1, t1.numOfLeaves());
		
		//degenerierten, listenartigen Baum erzeugen, der nur ein Blatt hat
		for (int n = 2; n <= 10; n++) {
			t1.insert(n);
		}	
		
		System.out.println("testNumOfLeaves1 - t1:");
		t1.print();
		int n1 = t1.numOfLeaves();
		System.out.println("NumOfLeaves: " + n1);
		
		assertEquals(n1, 1);
	}
	

	/** numOfLeaves-Methode f�r B�ume pr�fen */
	@Test
	public void testNumOfLeaves2() {
		//vollst�ndigen Baum der H�he 2 (d.h. mit 4 Bl�ttern)
		//erzeugen
		//         5
		//       /   \
		//      3     7
		//     / \   / \
		//    2   4 6   8
		SearchTree t2 = new SearchTree();
		t2.insert(5);
		t2.insert(3);
		t2.insert(7);
		
		t2.insert(2);
		t2.insert(4);
		t2.insert(6);
		t2.insert(8);

		System.out.println("testNumOfLeaves2 - t2:");
		t2.print();
		int n2 = t2.numOfLeaves();
		System.out.println("NumOfLeaves: " + n2);
		assertEquals(n2, 4);
	}
	
	/** numOfLeaves-Methode f�r B�ume pr�fen */
	@Test
	public void testNumOfLeaves3() {
		//Baum mit zwei Bl�ttern erzeugen	
		//		
		//		  5
		//      /   \
		//     3     7
		//    /     /
		//   2     6
		//
		SearchTree t3 = new SearchTree();
		t3.insert(5);
		t3.insert(3);
		t3.insert(7);
		t3.insert(2);
		t3.insert(6);

		System.out.println("testNumOfLeaves3 - t3:");
		t3.print();
		int n3 = t3.numOfLeaves();
		System.out.println("numOfLeaves: " + n3);

		assertEquals(n3, 2);
	}
	

	/** toList-Methode f�r B�ume pr�fen */
	@Test
	public void testToList1() {
		SearchTree tree = new SearchTree();
		ArrayList<Integer> list;
		
		//leerer Baum sollte leere Liste ergeben
		list = tree.toList();
		assertEquals(0, list.size());

		//10 unterschiedliche Werte (von 1 bis 10) in den Baum einf�gen
		for (int n : werte1) {
			tree.insert(n);
		}
		
		//Baum sollte jetzt Liste der L�nge 10 ergeben
		list = tree.toList();

		//Liste sollte aus den Elementen 1 bis 10 aufsteigend bestehen
		assertEquals(10, list.size());
		for (int i = 0; i < 10; i++) {
			int wert = list.get(i);
			assertEquals(i+1, wert);
		}	
	}

	/** toList-Methode f�r B�ume pr�fen */
	@Test
	public void testToList2() {
		SearchTree tree = new SearchTree();
		ArrayList<Integer> list;
		
		//10 unterschiedliche Werte (von 1 bis 10) in den Baum einf�gen
		//Reihenfolge entsprechend Feld
		for (int n : werte2) {
			tree.insert(n);
		}
		
		//Baum sollte jetzt Liste der L�nge 10 ergeben
		list = tree.toList();

		//Liste sollte aufsteigend aus den Elementen 1 bis 10 bestehen
		assertEquals(10, list.size());
		for (int i = 0; i < 10; i++) {
			int wert = list.get(i);
			assertEquals(i+1, wert);
		}	
	}

	
	/** extractMin-Methode f�r B�ume pr�fen */
	@Test
	public void testExtractMin_1() {
		//Baum mit 10 Werten aus dem Feld werte1 erzeugen
		SearchTree tree1 = new SearchTree();				
		for (int n : werte1) {
			tree1.insert(n);
		}
		
		//Baum mit Werten aus dem Feld werte2 erzeugen
		SearchTree tree2 = new SearchTree();
		for (int n : werte2) {
			tree2.insert(n);
		}

		//Baum mit Werten von 1 bis 10 erzeugen
		SearchTree tree3 = new SearchTree();
		for (int n = 1; n <= 10; n++) {
			tree3.insert(n);
		}
		
		//nacheinander jeweils die 10 kleinsten Elemente aus
		//den B�umen entnehmen. Sollt bei allen drei B�umen die Folge der
		//Zahlen von 1 bis 10 liefern
		for (int expected = 1; expected <= 10; expected++) {
			assertEquals(expected, tree1.extractMin());
			assertEquals(expected, tree2.extractMin());
			assertEquals(expected, tree3.extractMin());
		}
		
		//nun sollten die B�ume alle leer sein
		assertTrue(tree1.isEmpty());
		assertTrue(tree2.isEmpty());
		assertTrue(tree3.isEmpty());
	}
	
	/** extractMin-Methode f�r B�ume pr�fen */
	@Test
	public void testExtractMin_2() {
		//Baum mit 10 Werten aus dem Feld werte1 erzeugen
		SearchTree tree = new SearchTree();				
		for (int n : werte1) {
			tree.insert(n);
		}

		//die 5 kleinsten Werte entnehmen (1..5)
		assertEquals(1, tree.extractMin());
		assertEquals(2, tree.extractMin());
		assertEquals(3, tree.extractMin());
		assertEquals(4, tree.extractMin());
		assertEquals(5, tree.extractMin());
		
		//erneut die Werte 1 .. 5 eintragen
		tree.insert(4);
		tree.insert(2);
		tree.insert(1);
		tree.insert(3);
		tree.insert(5);
		
		//alle Werte mit extractMin entnehmen. Sollte 1..10 liefern
		for (int n = 1; n <= 10; n++) {
			assertEquals(n, tree.extractMin());
		}
		
		assertTrue(tree.isEmpty());
	}
	
	/** equals-Methode f�r B�ume pr�fen */
	@Test
	public void testEquals_1() {
		//Zwei B�ume mit den gleichen Werten, aber unterschiedlicher
		//Struktur aufbauen
		SearchTree tree1 = new SearchTree();
		for (int n : werte1) {
			tree1.insert(n);
		}
		
		SearchTree tree2 = new SearchTree();		
		for (int n : werte2) {
			tree2.insert(n);
		}
			
	    //Beide B�ume enthalten die gleichen Werte von 1 bis 10
		assertTrue(tree1.equals(tree2));
		assertTrue(tree2.equals(tree1));
	}
		
	/** equals-Methode f�r B�ume pr�fen */
	@Test
	public void testEquals_2() {
		//Baum mit den Werten 1 bis 10 sowie 42 erzeugen
		SearchTree tree1 = new SearchTree();
		for (int n : werte1) {
			tree1.insert(n);
		}
		tree1.insert(42);
		
		//Baum mit den Werten 1 bis 10 erzeugen
		SearchTree tree2 = new SearchTree();		
		for (int n : werte2) {
			tree2.insert(n);
		}
			
		//Beide B�ume sollten nicht gleich sein
		assertFalse(tree1.equals(tree2));
		assertFalse(tree2.equals(tree1));
	}
	
}
