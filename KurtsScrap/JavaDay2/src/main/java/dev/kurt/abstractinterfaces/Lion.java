package dev.kurt.abstractinterfaces;

public class Lion extends Animal implements Carnivore{

	public Lion()
	{
		super("Lion");
	}
	
	public void huntGazelles() {
		System.out.println("The lion is hunting Gazelles");
	}
	
	@Override
	public void makeSound() {
		System.out.println("Rawr xD!");
	}

	public void eatMeat() {
		System.out.println("Tears into a fresh gazelle with its huge teeth");
		
	}
	
	
}
