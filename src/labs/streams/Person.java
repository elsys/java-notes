package labs.streams;

import java.util.List;

public class Person {

	private String names;

	private Gender gender;

	private int age;

	private List<Person> friends;

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

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
}
