package dev.ranieri.wrappers;

public class WrapperPlayground {
	
	// Wrappers are the object versions of primitives
	// They are helpful because they can provide extra utility over primitives
	// They helpful methods. Additionally sometimes you need to pass in a number as an object 
	// for the method signature
	
	public static void main(String[] args) {
		
		//wrappers
		Integer i = -1000;
		Integer x = -1000;
		

		
		// Character
		// Double
		// Float
		// Long
		// Short
		// Byte
		// Boolean
		// Integers
		
		System.out.println(i == x);
		
		
		// Wrappers can unbox and autobox
		// unboxing. Wrapper turns itself into a primitive
//		print(x);
//		
//		// autoboxing a primitive is turned into a wrapper
//		double num = 90.56;
//		print(num);
//		
		
	}
	
	
	public static void print(int i) {
		System.out.println(i);
	}
	
	public static void print(Double d) {
		System.out.println("Printing from double");
		System.out.println(d);
	}

}
