package assignment2;


public class RowTest {

	/**
	 * Reorders all person in the row, by swapping, in such a way
	 * that all children are at the beginning of the row,  the 
	 * teenagers follow and adults are placed at the end of the row.
	 */
	public static void reorder(PersonRow m) {
            
            int cIndex = 0; // Child index
            int aIndex = m.size() - 1; // Adult index
            
            boolean foundChild = false;
            boolean foundAdult = false;
            
            // Set first cell to child, last cell to adult, teens go in between.
            for(int i=0; !(foundChild && foundAdult) && i<m.size(); i++){
                AgeGroup ag = m.getAgeGroup(i);
                
                // Looks for the first child and moves it to the start
                if(ag == AgeGroup.CHILD && !foundChild){
                    m.swap(i, cIndex);
                    foundChild = true;
                }
                
                // Looks for the first adult and moves it to the end
                if(ag == AgeGroup.ADULT && !foundAdult){
                    m.swap(i, aIndex);
                    foundAdult = true;
                }
            }
            
            // Iterating all the cells in between the first child and last adult
            int i = cIndex + 1;
            
            while(i<m.size() - 1){
                AgeGroup ag = m.getAgeGroup(i);
                if(ag == AgeGroup.CHILD){
                    m.swap(i,cIndex+1);
                    i++;
                    cIndex++;
                }else if(ag == AgeGroup.ADULT){
                    if(m.getAgeGroup(aIndex-1) == AgeGroup.ADULT)
                        aIndex--;
                    else{
                        m.swap(i, aIndex-1);
                        i++;
                        aIndex++;
                    }
                }else{
                    i++;
                }  
            }
	}
	
	
	public static void main(String[] args) {
		demo01();
		System.out.println();
		
		demo02();
		System.out.println();
		
		runtimeMeasurement();
		
		System.out.println("- done -");

	}
	
	public static void demo01() {
		System.out.println("demo01: ");
		PersonRow m1 = new PersonRow(100);
		m1.add(new Person(AgeGroup.ADULT, "Anna"));
		m1.add(new Person(AgeGroup.CHILD, "Berta"));
		m1.add(new Person(AgeGroup.TEENAGER, "Caesar"));
		m1.add(new Person(AgeGroup.ADULT, "Dora"));
		m1.add(new Person(AgeGroup.CHILD, "Emil"));

		m1.swap(2, 4);
		m1.print();
		System.out.println("? Correctly ordered: " + m1.isOrdered());

		System.out.println("--- reorder ---:");

		reorder(m1);
		m1.print();
		System.out.println("? Correctly ordered: " + m1.isOrdered());
		System.out.println();
	}

	
	public static void demo02() {
		System.out.println("demo02: ");
		PersonRow m2 = PersonRow.generateRandomRow(50);
		System.out.println("? Correctly ordered: " + m2.isOrdered());

		System.out.println("--- reorder ---:");

		reorder(m2);
		m2.print();
		System.out.println("? Correctly ordered: " + m2.isOrdered());	
	}

	public final static int MAX_ANZAHL = 10000000;

	public static void runtimeMeasurement() {
		System.out.println("Runtime: ");
		for (int count = 10; count <= MAX_ANZAHL; count *= 10) {
			double timeMS = runtimeMS(count);
			
		}
	}
	
	/** generates a random row of count persons and measures the running time
	 *  required to reorder the row.
	 * @param count	   number of persons in the row
	 * @return         running time for reordering in milli seconds
	 */
	
	public static double runtimeMS(int count) {
		System.out.printf("n = %9d...", count);
		PersonRow m = PersonRow.generateRandomRow(count);
		System.out.print("(generated): ");
		
		long start = System.nanoTime();
		reorder(m);
		long end = System.nanoTime();
		
		double timeMS = (end - start) / 1e6;
		System.out.printf("%8.3f ms.%n", timeMS);
		return  timeMS;
		
	}
	
	
}
