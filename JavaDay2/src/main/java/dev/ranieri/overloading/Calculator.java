package dev.ranieri.overloading;

// a utility class for performing mathematical operations
// a utility class is a class with a bunch of helpful static methods
public class Calculator {
	
	
	
	public static int add(String a, String b) {
		int num1 = Integer.parseInt(a);
		int num2 = Integer.parseInt(b);
		
		return num1 +num2;			
	}
	
	public static int add(int a, int b) {
		return a+b;
	}
	
//	public static int add(int [] ray) {
//		
//		int sum = 0;
//		
//		// enhanced for loop iterates over every element in this array
//		for(int num : ray) {
//			sum = sum + num;
//		}
//		
//		return sum;
//		
//	}
//	
  // varargs (variable arguments) it is like taking in an array
	// nums here is treated like an array when passed int
	public static int add(int... nums) {

		int sum = 0;
		
		// enhanced for loop iterates over every element in this array
		for(int num : nums) {
			sum = sum + num;
		}	
		return sum;
	}
	
	
}
