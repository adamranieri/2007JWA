package dev.ranieri.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class LambdaPlayground {
	
	
	public static void main(String[] args) {
		
		// A lambda is a piece of functional code that is treated like an object
		// This object can be stored in a variable or passed as a parameter like any other object
		
		Runnable helloFunction = () -> {			
			System.out.println("Hello Everyone Great to see you all !!!");			

		};
		
		Runnable goodByeFunction = () -> {			
			System.out.println("Bye everyone it has been great!!!");				
		};
		
		
		// callback function. Passes a lambda-function as a parameter to another function
		// not invoking the goodByeFunction immediately when I pass it in
		saySomething(goodByeFunction);
		
		System.out.println("End of Lambda Playground");	
		
		
		String [] names = {"Jaxon","Maxewell", "John","William", "Kate","Jesse"};
		
		List<String> names2 = Arrays.asList(names);
		
		// iterate over the array and you have code that prints out the name
		for(String name : names2) {
			System.out.println(name);
		}
		
		// iterate over the array but you pass in the code that prints out the name		
		Consumer<Object> print = (name)->{System.out.println(name);};
		
		names2.forEach(print);
		
		
		
	}

	public static void saySomething(Runnable r) {
		
		r.run();
	}
}
