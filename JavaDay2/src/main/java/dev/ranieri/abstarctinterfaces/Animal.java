package dev.ranieri.abstarctinterfaces;

// An abstract class is just a class that you cannot instantiate directly
public abstract class Animal {

	
	String species;
	
	
	// an abstract method is a method that has no implementation
	// child classes must create an implementation 
	public abstract void makeSound();


	public Animal(String species) {
		super();
		this.species = species;
	}


	@Override
	public String toString() {
		return "Animal [species=" + species + "]";
	}
	
	
}
