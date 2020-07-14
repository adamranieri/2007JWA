package dev.ranieri.generics;

import java.util.ArrayList;
import java.util.List;

// this is now a class that can be created with a Generic
// You can write your method signatures and variables with T later replaced by another class
public class MyAwesomeList<T> {
	
	private List<T> hiddenList = new ArrayList<T>();
	
	void printInstance(T t) {	
		System.out.println(t);	
	}
	
	void addToList(T t) {
		hiddenList.add(t);
	}
	
	void printAll() {
		
		for(T thing : hiddenList ) {
			System.out.println(thing);
		}
	}

}
