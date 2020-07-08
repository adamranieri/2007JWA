package dev.kurt.abstractinterfaces;

public class Dog extends Animal implements Carnivore{

	public Dog() {
		super("Dog");

	}

	@Override
	public void makeSound() {
		System.out.println("BorK bOoorK woof woo00f BARK");
		
	}

	public void eatMeat() {
		System.out.println("Eats bacon and kibble");
		
	}

}
