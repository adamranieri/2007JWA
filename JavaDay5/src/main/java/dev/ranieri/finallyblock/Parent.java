package dev.ranieri.finallyblock;

// you can put final on a class to prevent it from being inherited
// Scanner and String are examples of Final classes
public final class Parent {

	// 2. you can put final on a method to prevent it from being overridden
	final void hello() {
		System.out.println("Hello!!!!");
	}
	
	 void hello(String name) {
		System.out.println("Hello!!!!");
	}
}
