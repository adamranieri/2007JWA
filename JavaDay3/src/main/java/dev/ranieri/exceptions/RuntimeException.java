package dev.ranieri.exceptions;

public class RuntimeException {

	
	public static void main(String[] args) {
		
		
		int [] nums = {1,2,3};
		try {
			// runtime exceptions are not required to be handled
			System.out.println(nums[4]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("You accessed an element that does not exist");
		}
	
	}
}
