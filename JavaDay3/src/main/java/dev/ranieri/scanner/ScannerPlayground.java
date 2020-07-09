package dev.ranieri.scanner;

import java.util.Scanner;

public class ScannerPlayground {

	public static Scanner scan = new Scanner(System.in); // you only ever want one scanner in your application
	
	public static void main(String[] args) {
		
		System.out.println("What is your name?");
		// scanner is a very finnicky class that will give you some input problems
		String name = scan.next();		
		System.out.println("Hello" + name);
		
		scan.close();
	}

	
}
