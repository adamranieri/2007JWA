package dev.ranieri.abstarctinterfaces;

public class Cow extends Animal implements Herbivore, Petable{

	public Cow() {
		super("Cow");
	}
	
	@Override
	public void makeSound() {
		System.out.println("MooOOOoOOoOO");
	}

	public void eatPlant() {
		System.out.println("Eats some grass");
	}

}
