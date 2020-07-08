package dev.ranieri.abstarctinterfaces;

// Interfaces must have only abstract methods * a loophole in Java 8
// Interfaces do not have instance variables

//any variables in an interface must be public static and final
// everything in an interface is public
public interface Herbivore {
	
	// even if you do not include public static final it is always public static final
	public static final String desc = "A Herbivore only eats plants";
	
	public void eatPlant();

}
