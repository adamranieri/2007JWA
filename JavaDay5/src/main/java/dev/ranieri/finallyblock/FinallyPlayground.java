package dev.ranieri.finallyblock;

public class FinallyPlayground {
	
	
	public static void main(String[] args) {
		
		try {
			int [] nums = {1,2,3,4};		
			System.out.println(nums[1]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("catch block");
		}finally {
			// finally blocks are helpful for when you need to close resources
			// Regardless of if saving the object to the database was successful please close the connection
			System.out.println("Finally block always executes regardless of if an exception is thrown or not");
		}
		
		// Java you can have just a try finally block
		try {
			
		}finally{
			
		}
		
		
	}

}
