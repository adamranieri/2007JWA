package DesignPatterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// A factory is a design pattern
// A design pattern is a code conventional solution to a common problem
// Factory design pattern is for when you need an instance of an interface,
// but dont know what implementation class you need
public class ArrayFactory {
	
	public static List getArray(int choice) {
		/**
		 * This factory method is designed to return you the best array for your application
		 * enter 1 for reading efficiency
		 * enter 2 for insertion efficiency 
		 * 
		 * @param choice
		 * @return
		 */
		if(choice == 1) {
			return new ArrayList();
		}else {
			return new LinkedList();
		}
		
	}

}
