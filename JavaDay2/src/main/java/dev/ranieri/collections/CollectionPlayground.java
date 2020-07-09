package dev.ranieri.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionPlayground {
	
	public static void main(String[] args) {
		
		Person jaxon = new Person("Jaxon", 23, 74);
		Person peter = new Person("Peter", 25, 79);
		Person jackie = new Person("Jackie", 22,62);
		Person art = new Person("Arthur", 28, 70);
		
		// cons to storing in an array
		// they have a fixed size
		// they lack helpful methods
		
		// lists maintain the order of insertion
		// can contain duplicates
		
		// <> diamond brackets or angular brackets
		// In Java they signify generics. They specify what type is allowed for that class/interface
		List<Person> people = new ArrayList<Person>();
		people.add(art);
		people.add(jackie);
		people.add(peter);
		people.add(jaxon);
		
		
		// sets do not maintain the order of insertion
		// cannot contain duplicates
		Set<Person> setPeople = new HashSet<Person>();
		setPeople.add(art);
		setPeople.add(jackie);
		setPeople.add(jaxon);
		setPeople.add(jackie);
		setPeople.add(peter);
		setPeople.add(jackie);

		
		System.out.println(setPeople);
		
		// maps work as key value pairs. You put in a key you get out a value
		// you cannot iterate over a map
		Map<String, Person> map = new HashMap<String,Person>();
		map.put("Art", art);
		map.put("Jaxon", jaxon);
		map.put("Jack", jackie);
		map.put("Jackie", jackie);
		
		System.out.println(map.get("Jackie"));
		
		
		
	}

}
