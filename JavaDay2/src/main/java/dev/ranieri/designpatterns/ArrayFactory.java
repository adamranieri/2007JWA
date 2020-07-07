package dev.ranieri.designpatterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// A factory is a design pattern
// A design pattern is a code design solution to a common problem
// Factory design pattern is for when you need an instance of an interface but do not know what 
// implementation class you need
public class ArrayFactory {

	/**
	 * This factory method is designed to return you the best array for your application
	 * enter 1 for reading efficiency
	 * enter 2 for insertion efficiency 
	 * 
	 * @param choice - numerical option to select array
	 * @return List best suited for your collection
	 */
	public static List getArray(int choice) {
		
		if(choice == 1) {
			return new ArrayList();
		}else {
			return new LinkedList();
		}
		
	}
}
