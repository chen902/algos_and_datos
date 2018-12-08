import java.util.Random;


/**
 * Measure runtime of some search tree operations
 */
public class SearchTreeMeasurement
{
	public static final int MIN_N = 10;
	public static final int MAX_N = 10000000;
	
    public static void main(String[] args) {
    	System.out.println("Runtime measurement insert()+extractMin():");
    	for (int n = MIN_N; n <= MAX_N; n *= 10) {
    		runtimeExtractMin(n);
    	}
    			
 		System.out.println();
    			
    	System.out.println("Runtime measurement equals():");

    	for (int n = MIN_N; n <= MAX_N; n *= 10) {
    		runtimeEquals(n);
    	}
        
        System.out.println("\n- done -");
        
    }

    private static final int REPEAT = 1000;
    
    public static void runtimeExtractMin(int n) { 
    	SearchTree tree = new SearchTree();
    	Random rand = new Random();
    	
    	// insert n random values into tree
    	for (int i = 0; i < n; i++) {
    		int value = rand.nextInt(100*n);
    		tree.insert(value);
    	}
    
    	// 1000 times extract a value with extractMin() and insert a random value with insert()
    	
    	// compute 1000 random values
    	int [] newValues = new int[REPEAT];
    	for (int i = 0; i < newValues.length; i++) {
    		newValues[i] =  rand.nextInt(100*n);
    	}
    	
    	// 1000 times extract and insert values
    	long tStart = System.nanoTime();
    	for (int i = 0; i < REPEAT; i++) {
    		tree.extractMin();
    		tree.insert(newValues[i]);
    	}
    	long tEnde = System.nanoTime();
    	
    	System.out.printf("n = %10d: runtime per extractMin+insert %8.1f usec. %n", n, (tEnde - tStart)/1000.0);
    	
    }
   
    public static void runtimeEquals(int n) { 
 
         	
    	//compute n random values
    	Random rand = new Random();
    	int[] werte = new int[n];
    	for (int i = 0; i < werte.length; i++) {
    		werte[i] = rand.nextInt();
    	}
    	
    	// insert values into the tree
    	SearchTree t1 = new SearchTree();
    	long zeitInsert1 = 0;
    	for (int i = 0; i < werte.length; i++) {
    		int v = werte[i];
    		long start = System.nanoTime();
    		t1.insert(v);
    		zeitInsert1 += System.nanoTime() - start;
    	}
    	
    	// insert same values in reverse order int tree t2.
    	// so t2 contains the same values as t1, but has another
    	// tree structure
    	SearchTree t2 = new SearchTree();
    	long zeitInsert2 = 0;
    	for (int i = werte.length-1; i >= 0; i--) {
    		int v = werte[i];
    		long start = System.nanoTime();
    		t2.insert(v);
    		zeitInsert2 += System.nanoTime() - start;
    	}
    	
//    	System.out.println("- t1.insert (total): " + (zeitInsert1/1.0e6) + " msec");
//    	System.out.println("- t2.insert (total): " + (zeitInsert2/1.0e6) + " msec");
//    	System.out.println("- height of t1: " + t1.height());
//    	System.out.println("- heigth of t2: " + t2.height());
    	
		long start1 = System.nanoTime();
		boolean result1 = t1.equals(t2);
		long dauer1 = System.nanoTime() - start1;

		System.out.printf("n = %8d: %6b, runtime %8.2f msec. %n", n, result1, dauer1 / 1.0e6);    	
    	
    	
    }

}
