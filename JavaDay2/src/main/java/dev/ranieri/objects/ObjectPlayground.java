package dev.ranieri.objects;


public class ObjectPlayground {
	
	// Java is a 99% OOP language.
	// Every object you make is of type Object
	// Every class ultimately inherits from the Object class
	// The object class has a few methods that everything in Java should be able to do

	public static void main(String[] args) {
		
		
		Object obj = new Object();
		
		// Any class you create gets a toString method
		// the toString method returns a string that represents the object
		// if you do not override the object toString method you will get the memory location
		
		// Overriding is the process by which a child changes the implementation of an inherited method
		
		Person ryan = new Person("Ryan", 26,"Trainer");
		
		// any print statement that involves an object implicityly calls toString
		System.out.println(ryan);
		System.out.println(ryan.toString()); // exactly the same thing
	}

}
