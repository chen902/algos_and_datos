package assignment1;

import static java.lang.Math.pow;
import java.util.TreeSet;

public class Exercise_1_8_Strings {

        /*
        * Generates all the possible combinations of a given character set in 
        * a given length.
        */
	public static TreeSet<String> generateStrings(char[] chSet, int length) {
            
            TreeSet<String> result = new TreeSet<>();
            
            if (length <= 0){
                result.add(""); // To match the JUnit expectation.
                return result;
            }
            
            int numOfChars = chSet.length;        
            
            // An array of indices for the character array. The individual cells
            // act as digits in a numerical system with base <chSet.length>
            // By incrementing, all possible combinations of the indices values are created.
            // They are then used to reference the characters.
            int[] indices = new int[length];

            // There are (number_of_symbols)^(number_of_digits) different combinations.
            int iterations = (int)pow(numOfChars,indices.length);

            // One iteration per combination
            for(int i=0; i < iterations; i++){
                // Traverses the indices array and increments as if it's a single number.
                int index = 0;
                String line = "";
                for(;index < indices.length-1; index++){
                    // If a digit exceeds the counting base, a carry is created.
                    if(indices[index] == numOfChars){
                        indices[index] = indices[index] % numOfChars;
                        indices[index+1]++;
                    }                    
                    // Appends the right character to the string
                    line += chSet[indices[index]];
                }
                // Appends the last character
                line += chSet[indices[index]];
                
                result.add(line);
                indices[0]++; // Increment the "least significan digit"
            }
            return result;
        }
    

	
	
	public static void main(String[] args) {
		char[] characters = { 'A', 'B', 'C', 'D', 'E' };
		int count = 0;
		for (String sequence : generateStrings(characters, 4)) {
			count++;
			System.out.printf("%5d. %s%n", count, sequence);
		}

	}
}
