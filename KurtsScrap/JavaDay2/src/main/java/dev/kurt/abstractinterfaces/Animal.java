package dev.kurt.abstractinterfaces;

//abstract class is just a class that you cannot instantiate directly
public abstract class Animal {
	
	String species;
	
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
