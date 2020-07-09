package dev.ranieri.exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExceptions {
	
	public static void main(String[] args) {
		
		// only one catch will ever execute
		// the catch block that is activated will be the most specific one that matches
		// whenever you write catch blocks you must order them from most specific to least specific
		try {
			readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static  void readFile() throws FileNotFoundException, Exception {
		File f = new File("sea:unicorns/leprachauns/magic.txt");	
		
		// we have two choices whenever we encounter a checked exception
		// 1. immediately put it in a try catch 
		// 2. add throws declaration to the method signature. 
		// (Passing on the responsibility of handling it to caller)
		
		FileReader reader = new FileReader(f);
		
		throw new Exception("Random crazy excpetion that just happens");
	}

}
