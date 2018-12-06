package assignment4.exercise4_5_priolist;

/** 
 * Entry for priority queues.
 * Each entry consists of a value and a priority. A smaller priority number indicates a higher priority
 * 
 * @author schied
 *
 * @param <E> type of values to be stored
 */
public class Entry<E> {
	/** stored value */
	private E value;
	
	/** priority */
	private int prio;
	
	/** initializes a new entry with the given value and priority
	 */
	public Entry(E value, int prio) {
		this.value = value;
		this.prio = prio;
	}
	
	/**returns value of the entry */
	public E getValue() {
		return value;
	}

	/** returns priority of the entry */
	public int getPriority() {
		return prio;
	}
	
	/** returns a string representation of the entry, containing value and priority
	 */
	public String toString() {
		return value.toString() + " (" + prio + ")"; 
	}
}
