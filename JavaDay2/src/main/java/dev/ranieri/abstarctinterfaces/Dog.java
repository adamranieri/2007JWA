package dev.ranieri.abstarctinterfaces;

// you can inherit only one class but implement as many interfaces you want
public class Dog extends Animal implements Carnivore, Herbivore, Petable{

	public Dog() {
		super("Dog");

	}

	@Override
	public void makeSound() {
		System.out.println("Bark");
		
	}

	public void eatMeat() {
		System.out.println("Eats some kibble with bacon in the food dish");
	}

	public void eatPlant() {
		System.out.println("Eats eco friendly vegamax carrot dog food");
	}

}
