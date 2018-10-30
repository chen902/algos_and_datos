package assignment1;

import java.util.Arrays;

public class Exercise_1_4_Containment {

	/**  
	 * Checks if every value contained in a1 is also contained in a2 */
	public static boolean allContained(long[] a1, long[] a2) {
            // Empty set is a subset of anything
            if(a1.length == 0)
                return true;            
            // Non empty set cannot be a subset of an empty set
            if (a1.length != 0 && a2.length == 0)
                return false;
            
            // Both arrays are sorted
            Arrays.sort(a1);
            Arrays.sort(a2);
            
            int a1_index=0;
            int a2_index=0;
            
            // If content of first cell in both arrays is differnt, 
            // no further steps are required.
            boolean isSubset = a1[a1_index] == a2[a2_index];
            a1_index++; a2_index++;
            
            // Starting from the second cell until reaching the end of any of the arrays.
            while(isSubset && a1_index < a1.length && a2_index < a2.length){
                // Duplicate in a1
                if(a1[a1_index] == a1[a1_index-1])
                    a1_index++;
                
                // Duplicate in a2
                if(a2[a2_index] == a2[a2_index-1])
                    a2_index++;
                
                // A new value was reached in both arrays
                if(a1_index < a1.length && 
                   a2_index < a2.length && 
                   a1[a1_index] != a1[a1_index-1] && 
                   a2[a2_index] != a2[a2_index-1]){
                
                    // If its the same value, move on to next cell in both arrays.
                    // If not, keep looking for current value in a2.
                    if(a1[a1_index] != a2[a2_index]){
                        a2_index++;
                    }
                    else{
                        a1_index++; 
                        a2_index++;
                    }
                }
            }
            
            // Last indexed cells would have equal value if a1 is contained on a2.
            if (a1_index == a1.length && (a1[a1_index - 1] == a2[a2_index - 1]))
                isSubset = true;
            else
                
                isSubset = false;
            
            return isSubset;
	}
	

	public static final int MAX_LEN = 10000000;

	public static void main(String[] args) {
		long[] m0 = {};
		long[] m1 = { 1, 2, 5 };
		long[] m2 = { 4, 3, 2, 1, 8, 7, 6, 5 };
		long[] m3 = { 2, 1, 5 };
		long[] m4 = { 2, 1, 4, 3, 2 };
		
		System.out.println("m0 subset of m1: result " + allContained(m0, m1) + " | expected: true");
		System.out.println("m0 subset of m2: result " + allContained(m0, m2) + " | expected: true");
		System.out.println("m0 subset of m3: result " + allContained(m0, m3) + " | expected: true");
		System.out.println("m0 subset of m4: result " + allContained(m0, m4) + " | expected: true");
		System.out.println("m1 subset of m0: result " + allContained(m1, m0) + " | expected: false");
		System.out.println("m1 subset of m2: result " + allContained(m1, m2) + " | expected: true");
		System.out.println("m2 subset of m1: result " + allContained(m2, m1) + " | expected: false");
		System.out.println("m0 subset of m0: result " + allContained(m0, m0) + " | expected: true");
		System.out.println("m1 subset of m1: result " + allContained(m1, m1) + " | expected: true");
		System.out.println("m1 subset of m3: result " + allContained(m1, m3) + " | expected: true");
		System.out.println("m3 subset of m1: result " + allContained(m3, m1) + " | expected: true");
		System.out.println("m4 subset of m0: result " + allContained(m4, m0) + " | expected: false");
		System.out.println("m4 subset of m1: result " + allContained(m4, m1) + " | expected: false");
		System.out.println("m4 subset of m2: result " + allContained(m4, m2) + " | expected: true");
		System.out.println("m4 subset of m3: result " + allContained(m4, m3) + " | expected: false");
		System.out.println("m4 subset of m4: result " + allContained(m4, m4) + " | expected: true");

		// Running time test:
		for (int len = 100; len <= MAX_LEN; len *= 10) {
			System.out.println("Array length " + len + ": ");
			long[] a1 = new long[len];
			long[] a2 = new long[len];

			for (int i = 0; i < len; i++) {
				a1[i] = i;
				a2[len - 1 - i] = i;
			}
			long start = System.nanoTime();
			boolean result = allContained(a1, a2);
			long ende = System.nanoTime();
			
			System.out.println("\ta1 contained in a2: " + result + " Running time: "
					+ ((ende - start) / 1e6) + " ms");
		}
	}

}
