package assignment4.exercise4_5_priolist;

public interface IPriorityQueue<E> {
	/** Eintrag (mit Wert und Priorit�t) in die Priorit�tenwarteschlange aufnehmen */
	void insert(Entry<E> e);
	
	/** Den Eintrag mit der h�chsten Priorit�t (= kleinster wert f�r Priorit�t) aus
	 *  der Warteschlange entnehmen
	 *  
	 * @return entnommener Eintrag (bzw. null, wenn Warteschlange leer
	 */
	Entry<E> extractMin();
	
	/** Pr�ft, ob Warteschlange leer ist */
	boolean isEmpty();
	
	/** Pr�ft, ob Warteschlange voll ist */
	boolean isFull();
}
