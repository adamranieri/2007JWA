package dev.kurt.abstractinterfaces;

public class Cow extends Animal{
	
	public Cow()
	{
		super("Cow");
	}
	
	
	@Override
	public void makeSound() {
		System.out.println("ShazoooOOooOO");
	}
}
