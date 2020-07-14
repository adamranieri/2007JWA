package dev.ranieri.threading;

import java.util.ArrayList;
import java.util.List;

// Threading is the ability for a program to have multiple paths of execution running concurrently
// Program is a set of instructions. A thread executes part of those instructions. Your threads can
// run in parallel.

// pros: Can lead to faster execution (ability to work on multiple sub tasks at once)
// the main reason for multi-threading is increased efficiency

// cons: Concurrency issues : starvation, deadlock, livelock
// the main problem with multi-threading it adds overhead and complexity to your application.
// increases the chance that something goes wrong.

// deadlock - each thread has a resource the other thread will not give up first
// livelock (canadian lock) - Threads are two nice to each other and insist on giving up their resource first 

public class ThreadPlayground {
	
	public static int num = 100;
	
	public static void main(String[] args) {
		
		// making threads in Java
		// 1. define a piece of code for you thread to run/execute
		
		Runnable run1 = () ->{
			System.out.println("Hello from run1");
			num = num +100;
			System.out.println("Value of num " + num);
		};
		
		Runnable run2 = ()->{
			System.out.println("Hello from run2");
			num = num *2;
			System.out.println("Value of num " + num);
		};
		
		
		//2. create a thread object and pass in the code you want that thread to perform
		
		Thread t1 = new Thread(run1);
		Thread t2 = new Thread(run2);
		
		// the actual order of execution of threads is not determinate
		// this leads to a LOT of consistency issues if the threads work on shared objects
		// you must be very careful with threading when one thread's operation affects another thread
		
		// 3. The start method is what actually tells the thread object to create a new thread and execute 
		// the runnable lambda it was passed in
		t1.start();
		t2.start();
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		
		List<Thread> threads = new ArrayList<Thread>();
		threads.add(t2);
		threads.add(t1);
	}

}
