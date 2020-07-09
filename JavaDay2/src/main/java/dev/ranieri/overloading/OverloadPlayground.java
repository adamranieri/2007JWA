package dev.ranieri.overloading;

public class OverloadPlayground {
	
	// Overloading is when two methods have the same name but different parameters
	
	
	public static void main(String[] args) {
		
		// two methods with the same name different parameters
		int sum = Calculator.add(90, 85);
		int sum2 = Calculator.add("77", "6");
		
		int [] nums = {4,5,2,9,6,4};
		int sum3 = Calculator.add(nums);
		
		// you can use varargs syntax 
		// you can pass in an array or seperated arguments
		int sum4 = Calculator.add(9,3,1,5,3,5,9,4,2);
		
		System.out.println(sum);
		System.out.println(sum2);
		System.out.println(sum3);
		System.out.println(sum4);
		
		
		sayHello("Bonjur", "Tim", "Mike", "Nancy");
	
	}
	
	public static void sayHello(String greeting, String... names) {
		
		for(String name:names) {
			System.out.println(greeting + name);
		}
		
	}

}
