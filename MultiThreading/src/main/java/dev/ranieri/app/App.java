package dev.ranieri.app;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	public static Integer num = 0;

	public static void main(String[] args) {
		
		//Runnable is a functional interface
		// Interface in Java with one abstract method
		// allows yes to create a lambda function
		Runnable run = ()->{
					
				long start = System.nanoTime();
				for(int i =0; i<10;i++) {
					App.factorize(100000); // takes about 2 seconds to calculate
					System.out.println(Thread.currentThread().getName() + " iteration " +i);
					
				}
				long end = System.nanoTime();
				System.out.println("Total time in seconds " + (end-start)/1000000000);					
			
			
		};
		Runnable run2 = ()->{
			for(int i =0; i<10;i++) {
				factorize(50000); // takes about 1 second to calculate
				System.out.println(Thread.currentThread().getName() + " iteration " +i);

			}
			
		};
		
		// run is our callback function
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);
		Thread t4 = new Thread(run);
		Thread t5 = new Thread(run);
		Thread t6 = new Thread(run);
		Thread t7 = new Thread(run);
		Thread t8 = new Thread(run);

	
		
		t1.start(); 
		t2.start();
		t3.start(); 
		t4.start();
		t5.start(); 
		t6.start();
		t7.start(); 
		t8.start();
		// runs the runnable on the current rather than creating a new thread and new path of execution
		//System.out.println(Thread.currentThread().getName()); // with only one thread in the application
		// we are in the main thread
		
		
	}
	
	public static List<Integer> primeFactors(int number) {
		int n = number;
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
	}

	public static void factorize(int n) {

		for (int i = 0; i < n; i++) {
			primeFactors(i);
		}
	}
	
	

}
