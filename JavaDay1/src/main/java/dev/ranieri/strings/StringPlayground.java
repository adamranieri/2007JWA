package dev.ranieri.strings;

public class StringPlayground {
	
	public static void main(String[] args) {
		
		// Strings ARE OBJECTS NOT PRIMITIVES		
		String hello = "Hello"; //  literal syntax
		String hello2 = "Hello";
	
		
		// determining equality in Java
		// whether to values are equal to one another
		
		// == 
		// does a comparison based on value for primitives
		// memory location in Objects
		
		int x = 100;
		int y = 100;
		
		// these two objects are the same exact object in memory
		// hello and hello2 both point to the same location
		System.out.println(hello==hello2);
		
		// the 'new' keyword allocates a new memory address for whatever object you are creating
		// * unless new is being used you cannot be sure a new memory location is created
		String hola = new String("Hola");
		String hola2 = new String("Hola");
		
		System.out.println(hola == hola2);
		
		// morals of the story strings are NOT Primitives
		// Identical string share the same memory location 
		// == in objects compares memory location
		
		// under the hood terminology
		// Strings have a special section of memory called to string pool (heap)
		// All strings live in the string pool
		// Strings of the value are the same object in the pool
		
		// Strings are immutable you cannot change them once created.
		
		String name = "adam";
		System.out.println(name);
		String capName = name.toUpperCase(); // toUpperCase returns a NEW string that is the capitalized version
		System.out.println(capName);
		System.out.println(name);
		
		
		// StringBuilder is the mutable version of string
		// You should use Stringbuilder whenever you have to do a lot of character manipulation
		StringBuilder chipotle = new StringBuilder("Chipotle");
		System.out.println(chipotle);
		chipotle.append(": always an adventure!!");
		chipotle.reverse();
		System.out.println(chipotle);
		
		// situation 1 application where you are working with timesheets for a massive company
		// Smith and other commonly occuring strings occur constantly in the application // string saves memory
		
		// situation 2 application where you are working in Jurassic Park doing dino gene splicing algorithms
		// genetic code actggtcgatcgtgtgtcagtcgttagtcgtag can be 100,000's of characters in length
		// you need to ad a new segment of gene code towards the end 
		// StringBuilder
	
		
	}

}
