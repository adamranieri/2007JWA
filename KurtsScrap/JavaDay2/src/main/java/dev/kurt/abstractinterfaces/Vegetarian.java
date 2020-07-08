package dev.kurt.abstractinterfaces;

// Interfaces must have only abstract methods
// they dont have instance variables
public interface Vegetarian {

	//even if you do not include "public static final", it is "p-s-f"
	public static final String desc = "A vegetarian only eats plants";
	public void eatPlant();
	
}
