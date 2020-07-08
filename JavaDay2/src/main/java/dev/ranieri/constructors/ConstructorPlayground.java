package dev.ranieri.constructors;

public class ConstructorPlayground {

	// constructors are special methods that create an instance of a class
	// The only method in Java to not have a return type
	// They must be named the same as the class
	
	public static void main(String[] args) {
		
//		Dwelling hobbithole = new Dwelling(50, "Pippin");
//		System.out.println("Area : "+ hobbithole.area + "   Owner: " +  hobbithole.owner);
//		 Dwelling hobbithole2 = new Dwelling();// Does not work anymore
		// Java gives you a default constructor only if there are no constructors
		// If you do not have a consturcutor Java will provide one for you
//
		House h = new House(1200, "Gandalf", 8);
		System.out.println("area: " + h.area + " owner : "+ h.owner + " walls: " + h.walls);
		
		
	}
	
	
}
