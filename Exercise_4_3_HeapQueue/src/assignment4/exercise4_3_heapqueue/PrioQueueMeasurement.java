package assignment4.exercise4_3_heapqueue;

import java.util.Random;

import javax.swing.JOptionPane;

public class PrioQueueMeasurement {

	private final static int MIN_N = 10;
	private final static int MAX_N = 1000000;
	
	private final static Random rand = new Random();


	public static void main(String[] args) {

		int maxN = Integer.parseInt(JOptionPane.showInputDialog("Maximum size n = ", MAX_N));

		System.out.println("HeapQueue:");
		System.out.printf("| %10s | %14s | %15s | %15s |%n", "n", "n x insert", "100 x insert+extractMin", "n x extractMin");
		for (int n = MIN_N; n <= maxN; n *= 10) {
			IPriorityQueue<String> prioq = new HeapQueue<String>(n+1);
                        System.out.println("some");
			measurePQRuntime(prioq, n);
		}
		
		System.out.println("- done -");	
	}

	
	
	/** generates an entry with random value and priority
	 */
	public static Entry<String> randEntry(int maxValue) {
		int prio = rand.nextInt(maxValue);
		return new Entry<String>("V" + prio, prio);
	}

    /** 
     * Measures runtime of the following scenario:
     *  <ol>
     * 	    <item>insert n random entries </item> 
     *      <item>100 times insert and extract an entry  </item> 
     *      <item>extract all entries
     *  </ol>
     */
	public static void measurePQRuntime(IPriorityQueue<String> prioq, int n) {
	
		System.out.printf("| %10d |", n);
		
		// (1) insert n entries
		//     at first generate entries
		Entry<String>[] entryNew = new Entry[n];
		for (int i = 0; i < n; i++) {
			entryNew[i] = randEntry(Integer.MAX_VALUE);
		}
		
		// then insert entries (do not measure generation time)
		long s1 = System.nanoTime();
		for (int i = 0; i < n; i++) {

			prioq.insert(entryNew[i]);
		}
		long e1 = System.nanoTime();
		System.out.printf("%15.2f |", (e1 - s1) / 1e6 );
	 

		// (2) 100 times insert an entry and remove an entry
		//     generate 100 entries with random priority
		entryNew = new Entry[100];
		for (int i = 0; i < entryNew.length; i++) {
			entryNew[i] = randEntry(Integer.MAX_VALUE);
		}

		// repreatedly insert and extract entry
		long ta = System.nanoTime();
		for (int i = 0; i < entryNew.length; i++) {
			prioq.insert(entryNew[i]);
			Entry<String> next = prioq.extractMin();
		}
		long tb = System.nanoTime();
		System.out.printf("%24.2f |", (tb-ta)/1e6 );
		

		// (3) remove all n entries from the priority queue
		long s3 = System.nanoTime();
		while (!prioq.isEmpty()) {
			Entry<String> next = prioq.extractMin();
		}
		long e3 = System.nanoTime();
		System.out.printf("%15.2f |%n", (e3 - s3) / 1e6 );

	 
	}
}
