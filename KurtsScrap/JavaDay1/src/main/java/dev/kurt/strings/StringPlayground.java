package dev.kurt.strings;

public class StringPlayground {
	
	public static void main(String[] args) 
	{
		// Strings are OBJECTS NOT PRIMITIVES
		
		String hello = "Hello";
		String hola = "Hello";
		
		//determining equality in Java
		// whether 2 values are equal to one another
		
		
		// == 
		// does a comparison based on value, for primitives 
		// memory location for objects
		
		int x = 100;
		int y = 100;
		
		System.out.println(x==y);
		System.out.println(hola==hello);
		
		// the 'new' keyword
		// allocates a new memory address for whatever you are creating
		
		String hi = new String("Hi");
		String hi2 = new String("Hi");
		
		System.out.println(hi == hi2);
		
		/*some terminology:
		 * strings have a special section of memory called the string pool
		 * All strings live in the string pool
		 * strings of the same value are the same object in the pool
		 * 
		 * Strings are immutable
		 * You cannot change them once created
		 */
		
		String name = "Adam";
		
		System.out.println(name);
		//toUpperCase returns a NEW string that is capitalized
		String nameUpper = name.toUpperCase(); 
		System.out.println(nameUpper);
		
		String fname = "Bob";
		String lname = "Smith";
		
		System.out.println(fname + " " + lname);
		//4 strings have been created; fname, lname, " ", and "Bob Smith"
		
		// StringBuilder is the mutable version of string
		// Use when you need to do a lot of char manipulation
		
		StringBuilder chipotle = new StringBuilder("Chipotle");
		System.out.println(chipotle);
		chipotle.reverse();
		chipotle.append(": always an adventure");
		System.out.println(chipotle);
		//only 1 string was used here
		
		/* Use cases:
		 * 
		 * Situation 1: App working with timesheets for a massive company
		 */
		
		
	}

}
