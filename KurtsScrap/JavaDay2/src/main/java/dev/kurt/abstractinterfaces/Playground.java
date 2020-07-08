package dev.kurt.abstractinterfaces;

public class Playground {
	
	public static void main(String[] args) {
		
//		Animal a = new Animal("Giraffe");
//		
//		System.out.println(a);
//		a.makeSound();
		
		//you can label a child class as its parent
		//its more vague, so you might not be able to everything that you could if 
		//the reference variable was also the type of the child
		Animal l = new Lion();
//		
//		System.out.println(l);
//		l.makeSound();
//		l.huntGazelles();
		
		Animal c = new Cow();
//		c.makeSound();
		
		Animal d = new Dog();
		//at compile time, they're both animals. At runtime, 
		//JVM goes to their memory location and figures out they're a cow & lion
		
		Animal [] animals = {l,c,d};
		
		for(Animal animal: animals)
		{
			System.out.println(animal);
			animal.makeSound();
		}
		
		Carnivore carn = new Dog();
		carn.eatMeat();
		//upcasting is labeling something as more general
		//upcasting is always safe to do
		
		//likewise, downcasting is labeling something more specific
		//this is typically dangerous and can fail
		//if you find yourself downcasting, you have designed your application/code poorly
		
	}

}
