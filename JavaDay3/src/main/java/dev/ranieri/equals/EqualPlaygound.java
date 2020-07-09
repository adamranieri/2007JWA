package dev.ranieri.equals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EqualPlaygound {

	public static void main(String[] args) {
		
		Apple a = new Apple("Granny Smith", 700);	
		Apple b = new Apple("Honeycrisp", 850);
		Apple c = new Apple("Honeycrisp", 600);
		Apple d = new Apple("Golden Delicious", 720);
		
		List<Apple> apples = new ArrayList<Apple>();
		apples.add(a);
		apples.add(b);
		apples.add(c);
		apples.add(d);
		
		System.out.println(apples);
		
		//Collections utility class. contains helpful methods for working with collections
		Collections.sort(apples); // this method requires that your class in the collection implements comparable
		// comparable interface is referred to as the natural ordering of your class

		
		System.out.println(apples);
		
		
		// hashcodes serve as a hopefully unique value for your object
		// you cannot rely on proximity of hashcodes to one another to determine if two objects are similar
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		
		System.out.println(c == b);
		
		// you cannot  go in reverse where you can determine the initial state from the hashcode
		// (Almost completely) safe to assume objects with the same hashcode have the same field values
		// Does not mean they are the same object
		
		
		// When we want to compare if two objects are equal in Java we should use the equals method
		// its implementation is dependent on whatever the developer
		// equals method returns true or false if something is equal to another
		System.out.println(c.equals(b));
		
		// compareTo method which returns a number if what you compare it to is larger or smaller
		// -1 if smaller, 0 if the same 1 if larger
		
		System.out.println(a.compareTo(d));
		
	}
}
