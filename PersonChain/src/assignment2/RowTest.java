package assignment2;


public class RowTest {

	/**
	 * Reorders all person in the row, by swapping, in such a way
	 * that all children are at the beginning of the row,  the 
	 * teenagers follow and adults are placed at the end of the row.
	 */
	public static void reorder(PersonRow m) {
            
            int cIndex = 0; // Child boundary
            int aIndex = m.size() - 1; // Adult boundary
            
            // Do the boundaries already contain the proper values?
            boolean foundChild = m.getAgeGroup(cIndex) == AgeGroup.CHILD;
            boolean foundAdult = m.getAgeGroup(aIndex) == AgeGroup.ADULT;
            
            // Looks for the first CHILD and ADULT, swaps them to the boundaries
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
            
            // better safe then sorry
            if(!(foundAdult && foundChild))
                throw new RuntimeException("Missing values in PersonRow");
                
            
            // Iterating all the cells in between the first child and last adult
            int i = cIndex + 1;
        
            /*
            * First cell is CHILD and last is an ADULT, these are set as 'boundaries'.
            * Traversing all cells in between, encountered CHILD cells are swapped with the adjacent
            * cells to the boundaries and they are adjusted accordingly.
            */
            while(i<aIndex){ 
                AgeGroup ag = m.getAgeGroup(i);
        
                switch (ag) {
                    case CHILD:
                        // swap CHILD into place  
                        m.swap(i, cIndex + 1);
                        // new CHILD becomes the new boundary
                        cIndex++;
                        // next cell
                        i++;
                        break;
                    case ADULT:
                        // No need to swap ADULT with an ADULT just adjust the boundary
                        if(m.getAgeGroup(aIndex-1) == AgeGroup.ADULT)
                            aIndex--;
                        else{
                            // Swap and adjust boundary but stay on current cell
                            // so that next iteration it'll be checked
                            m.swap(i, aIndex-1);
                            aIndex--;
                        }   
                        break;
                    case TEENAGER:
                        // TEENAGER cells are just left untouched and should all
                        // end up in between the boundaries
                        i++;
                        break;
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
