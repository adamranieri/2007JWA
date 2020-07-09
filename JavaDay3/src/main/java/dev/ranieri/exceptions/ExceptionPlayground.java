package dev.ranieri.exceptions;

import java.util.Scanner;

public class ExceptionPlayground {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args)  {
		
		// Register a new User
		// String Username, String Password
		
		System.out.println("Welcome to our registration interface");
		System.out.println("Please input your username");
		String username = scan.next();
		System.out.println("Please input your password");
		String password = scan.next();
		
		try {
			// once an exception occurs in the try block you cannot go back to it 
			// anything below it does not execute
			signUp(username,password);
			System.out.println("end of try");
		} catch (InvalidLengthException e) {
			e.printStackTrace();
		
		}
		
		System.out.println("Application is closed");
		
		
	}
	
	public static void signUp(String username, String password) throws InvalidLengthException {
		
		if(username.length()<7 || password.length() <7) {
			String message = "Username length : " + username.length() + " Password length : " + password.length() +" both must be longer than 6 digits";	
			throw new InvalidLengthException(message);
		}
		
		
		
	}

}
