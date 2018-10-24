package assignment1;

import java.util.Arrays;

public class Exercise_1_7_ContainsCount {

	/** 
	 * Checks if value x is contained in arr[0..endIndex]
	 */
	public static boolean contains(double x, double[] arr, int endIndex) {
            // Deals with empty arrays
            if(arr.length == 0) 
                return false;
            
            // First cell of array reached or value was found. (stop conditions).
            if(endIndex-1 < 0 || arr[endIndex] == x)
                return arr[endIndex] == x;
            else
                // Starting from the end of the array and decrementing index.
                return contains(x, arr, endIndex-1);
	}

	/** 
	 * Determines how often character ch occurs in array arr 
	 */
	public static int count(char ch, char[] arr) {
			// TODO
            return -1;
	}

	

	public static void demoContains() {
		double[] a0 = {};
		double[] a1 = { 3.0 };
		double[] a2 = { 3.0, 4.0, 8.0 };

		System.out.println("a0: " + Arrays.toString(a0));
		System.out.println("3.0 contained in a0 enthalten: "
				+ contains(3.0, a0, a0.length - 1));
		System.out.println("4.0 contained in a0 enthalten: "
				+ contains(4.0, a0, a0.length - 1));

		System.out.println("a1: " + Arrays.toString(a1));
		System.out.println("3.0 contained in a1 enthalten: "
				+ contains(3.0, a1, a1.length - 1));
		System.out.println("4.0 contained in a1 enthalten: "
				+ contains(4.0, a1, a1.length - 1));

		System.out.println("a2: " + Arrays.toString(a2));
		System.out.println("3.0 contained in a2 enthalten: "
				+ contains(3.0, a2, a2.length - 1));
		System.out.println("4.0 contained in a2 enthalten: "
				+ contains(4.0, a2, a2.length - 1));
		System.out.println("5.0 contained in a2 enthalten: "
				+ contains(5.0, a2, a2.length - 1));
		System.out.println("8.0 contained in a2 enthalten: "
				+ contains(8.0, a2, a2.length - 1));
	}

	public static void demoCount() {
		char[] c1 = "mississippi".toCharArray();
		for (char ch = 'a'; ch <= 'z'; ch++){
			System.out.println("Count " + ch + ": " + count(ch, c1));
		};
		
	}

	public static void main(String[] args) {
		demoContains();
		System.out.println();
		demoCount();
	}

}
