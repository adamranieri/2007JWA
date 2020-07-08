package dev.ranieri.abstarctinterfaces;

public class Lion extends Animal implements Carnivore{

	public Lion() {
		super("Lion");
	}
	
	public void huntGazelles() {
		System.out.println("The lion is hunting gazelles on the savana");
	}

	@Override
	public void makeSound() {
		System.out.println("Roaroarroar !!!");
	}

	public void eatMeat() {
		System.out.println("Tears into a fresh gazelle with its huge teeth");
	}
}
