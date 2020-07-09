package dev.ranieri.scopes;

public class Box {
	
	
	// static variables tend to be variables that make sense even if there is no instance of the class	
	static String description = "A box is something that can hold things.";
	
	static int staticValue = 100;
	
	int instanceValue = 20;
		
	// name is a method scoped variable 
	// its the variables that are passed in 
	void sayHello(String name) {
		
		// greeting is a local variable because it is defined from within the method
		String greeting = "Hello to you";
		
		System.out.println(greeting + name);
	}

}
