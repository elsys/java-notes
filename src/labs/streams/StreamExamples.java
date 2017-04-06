package labs.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples {

	public static void main(String[] args) {
		List<Person> people = PersonUtils.generatePeople();
		
		System.out.println(allWomenNamesUnder25SortedByAgeAsc(people));
		
		// consuming a stream twice
		Stream<String> stream = people.stream()
				.filter(person -> person.getAge() < 25)
				.map(person -> person.getNames());
		System.out.println(stream.collect(Collectors.toList()));
		try {
			stream.forEach(name -> System.out.println(name));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		// friends of people using flatMap
		List<Person> friends = people.stream()
				.flatMap(person -> person.getFriends().stream())
				.collect(Collectors.toList());
		System.out.println(friends);
		
		// using distinct to find unique first names
		long uniqueCount = people.stream()
				.map(person -> person.getNames().split(" ")[0])
				.distinct()
				.count();
		System.out.println(uniqueCount);
		
		// using sort
		List<String> names = people.stream()
				.sorted((p1, p2) -> p1.getAge() - p2.getAge())
				.map(person -> person.getNames())
				.collect(Collectors.toList());
		System.out.println(names);
		
		people.stream()
				.skip(1)
				.limit(5)
				.collect(Collectors.toList());
		
		people.stream()
				.map(person -> person.getNames())
				.peek(personNames -> System.out.println(personNames))
				.count();

		long ageSum = people.stream()
				.map(person -> person.getAge())
				.reduce(0, (l, r) -> l + r);

		String namesSeparatedByComma = people.stream()
				.map(person -> person.getNames())
				.reduce("", (l, r) -> l + ", " + r);
		people.stream()
				.collect(Collectors.toCollection(() -> new SynchronousQueue<>()));
		
		Map<String, Gender> genderByPerson = people.stream()
				.collect(Collectors.toMap(
						person -> person.getNames(),
						person -> person.getGender(),
						(g1, g2) -> g1));
		
		Map<Gender, List<Person>> peopleByGender = people.stream()
				.collect(Collectors.groupingBy(person -> person.getGender()));
		
		Map<Boolean, List<Person>> byAgeLt25 = people.stream()
				.collect(Collectors.partitioningBy(person -> person.getAge() < 25));

		Map<Gender, List<String>> byGender = people.stream()
				.collect(Collectors.groupingBy(
						person -> person.getGender(),
						Collectors.mapping(person -> person.getNames(),
								Collectors.toList())));
	}

	public static List<String> allWomenNamesUnder25SortedByAgeAsc(
			List<Person> people) {
		List<Person> womenUnder25 = new ArrayList<>();
		for (Person person : people) {
			if (person.getGender() == Gender.FEMALE
					&& person.getAge() < 25) {
				womenUnder25.add(person);
			}
		}
		Collections.sort(womenUnder25, (p1, p2) -> p1.getAge() - p2.getAge());
		List<String> womenUnder25Names = new ArrayList<>();
		for (Person person : people) {
			womenUnder25Names.add(person.getNames());
		}
		return womenUnder25Names;
	}

	public static List<String> averageAgeOfWomenWithStreams(List<Person> people) {
		return people.stream()
				.filter(person -> person.getGender() == Gender.FEMALE)
				.filter(person -> person.getAge() < 25)
				.sorted((p1, p2) -> p1.getAge() - p2.getAge())
				.map(Person::getNames)
				.collect(Collectors.toList());
	}
}
