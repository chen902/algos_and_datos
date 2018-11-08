package assignment2;

/** Data of a person */
public class Person {
	private AgeGroup age;
	private String name;
	
	public Person(AgeGroup age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public String toString() {
		return name + " (" + age + ")";
	}

	public AgeGroup getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	
}