package labs.streams;

public class Person {

	private String names;

	private Gender gender;

	private int age;

	public Person(String names, Gender gender, int age) {
		this.names = names;
		this.gender = gender;
		this.age = age;
	}

	public String getNames() {
		return names;
	}

	public Gender getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}
}
