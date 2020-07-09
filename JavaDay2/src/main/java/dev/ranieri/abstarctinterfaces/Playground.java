package dev.ranieri.abstarctinterfaces;

public class Playground {
	
	public static void main(String[] args) {
		
//		Animal a = new Animal("wasp");
//		
//		System.out.println(a);
//		a.makeSound();
		
		// you can label a child class as its parent. That is a valid description of the object
		// more vague so you might not be able to do everything that you could if the refernce variable was
		// also the type of child
		
		//reference to an object
		//                          this new lion is always a lion. It will never be something else
		Lion l = new Lion();
		
		System.out.println(l);
		l.makeSound(); // still makes the sound of a lion not an animal
		l.huntGazelles();// not all animals can hunt gazelles
		l.eatMeat();
		
		Cow c = new Cow();
		c.makeSound();
		// c.huntGazlles();
		
		Dog d = new Dog();

		Animal h = new Human();
		
		// up casting is labeling something as more general (always safe to do)
		// down casting is labeling something more specific than what it is (dangerous and can fail)
		// if you find yourself downcasting you have designed you application/code wrong
		
		Petable [] pettingZoo = {d,c};
		System.out.println(h instanceof Petable);
	}

}
