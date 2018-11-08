package assignment2;

/**
 * Describes a sequence of people standing in a row.
 * 
 * @author G. Schied, Hochschule Ulm
 *
 */
public class PersonRow {

	/** Stores a row of persons */
	private Person[] chain;

	/** Number of persons in the row */
	private int count = 0;

	/** generates a row object with the given capacity
	 * 
	 */
	public PersonRow(int capacity) {
		chain = new Person[capacity];
	}

	
	/**
	 * Adds another person to the end of the row
	 * 
	 * @param p a person
	 */
	public void add(Person p) {
		chain[count] = p;
		count++;
	}

	
	/** returns the number of persons in the row */
	public int size() {
		return count;
	}

	/**
	 * Returns the age group of the person standing at position i in the row.
	 * Position numbers start with index 0.
	 * 
	 * @param index
	 *            position within the row
	 * @return age group of person standing at position index
	 */
	public AgeGroup getAgeGroup(int index) {
		return chain[index].getAge();
	}


	/**
	 * Exchanges persons at position i and j  in the row (where i < count and j < count)
	 */
	public void swap(int i, int j) {
		Person tmp = chain[i];
		chain[i] = chain[j];
		chain[j] = tmp;
	}

	/**
	 * Prints all persons in the row
	 */
	public void print() {
		System.out.println("Chain of " + count + " persons:");
		for (int i = 0; i < count; i++) {
			System.out.println(chain[i]);
		}
	}

	/**
	 * generates a row of persons with randomly chosen age groups
	 */
	public static PersonRow generateRandomRow(int size) {
		PersonRow m = new PersonRow(size);
		for (int i = 0; i < size; i++) {
			double rnd = Math.random();
			if (rnd < 0.33) {
				m.add(new Person(AgeGroup.CHILD, "child_" + i));
			} else if (rnd < 0.66) {
				m.add(new Person(AgeGroup.TEENAGER, "teenager_" + i));
			} else {
				m.add(new Person(AgeGroup.ADULT, "adult_" + i));
			}
		}

		return m;
	}

	/**
	 * Checks whether the persons in the chain are correctly arranged in the order
	 * children - teenagers - adults.
	 */
	public boolean isOrdered() {
		AgeGroup currentGroup = AgeGroup.CHILD;
		
		for (int i = 0; i < count; i++) {
			AgeGroup age = chain[i].getAge();
			
			switch (currentGroup) {
			case CHILD:
				currentGroup = age;
				break;
			case TEENAGER:
				if (age == AgeGroup.CHILD) {
					return false; // child after teenager
				}
				currentGroup = age;
				break;
			case ADULT:
				if (age == AgeGroup.CHILD || age == AgeGroup.TEENAGER) {
					return false; // child or teenager after adult
				}
			}
		}
		return true;
	}

}
