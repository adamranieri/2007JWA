package dev.ranieri.designpatterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FactoryDesignPattern {

	public static void main(String[] args) {

		double start = 0;
		double end = 0;
		double result = 0;

		// you as the developer need not know the implementation class
		// code to the interfaces
		List things = ArrayFactory.getArray(1);		
		
		start = System.nanoTime();
		for(int i = 0; i<100000;i++) {
			things.add(new Object());
		}
		end = System.nanoTime();
		result = end -start;
		
		System.out.println("Time to add 100,000 objects : "+ result/1000000000 );
		
		
		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		
		start = System.nanoTime();
		for(int i = 0; i<100000;i++) {
			things.get(50000);
		}
		end = System.nanoTime();
		result = end -start;

		System.out.println("Time to retrieve middle object 100,000 times : "+ result/1000000000 );
		
		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
				
		start = System.nanoTime();
		for(int i = 0; i<100000;i++) {
			things.add(0, new Object());
		}
		end = System.nanoTime();
		result = end -start;

		System.out.println("Time to insert at the begining 100,000 times : "+ result/1000000000 );
		
		
	}

}
