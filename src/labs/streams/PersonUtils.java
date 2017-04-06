package labs.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonUtils {

	public static void main(String[] args) {

		// prior to Java 8 approach
		List<Person> people = generatePeople();
		filter(people, new Filter() {

			@Override
			public boolean matches(Person person) {
				return person.getAge() > 12 && person.getAge() < 65 && person.getGender() == Gender.FEMALE;
			}

		});

		// Java 8 lambda syntax
		filter(new ArrayList<>(), person -> person.getAge() > 12);

		people.sort((p1, p2) -> p1.getAge() - p2.getAge());

		// lambda functions on many rows
		people.sort((p1, p2) -> {
			int delta = p1.getAge() - p2.getAge();
			return delta;
		});
		people.forEach(person -> {
			if (person.getAge() > 65) {
				System.out.printf("%s, 65+\n", person.getNames());
			} else {
				System.out.printf("%s, %d\n", person.getNames(), person.getAge());
			}
		});

	}

	public static List<Person> generatePeople() {
		return Arrays.asList(new Person("Ivan", Gender.MALE, 22),
				new Person("Ivanka", Gender.FEMALE, 34),
				new Person("Peter", Gender.OTHER, 66));
	}

	public static List<Person> filterByAgeLessThan(List<Person> people, int age) {
		List<Person> result = new ArrayList<>();
		for (Person person : people) {
			if (person.getAge() < age) {
				result.add(person);
			}
		}
		return result;
	}

	public static List<Person> filterByAgeAndGender(List<Person> people, int age, Gender gender) {
		List<Person> result = new ArrayList<>();
		for (Person person : people) {
			if (person.getAge() == age && person.getGender() == gender) {
				result.add(person);
			}
		}
		return result;
	}

	public static List<Person> filter(List<Person> people, Filter filter) {
		List<Person> result = new ArrayList<>();
		for (Person person : people) {
			if (filter.matches(person)) {
				result.add(person);
			}
		}
		return result;
	}

}
