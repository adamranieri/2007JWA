package dev.ranieri.constructors;



// extends keyword allows us to inherit the methods and fields of the parent class
// Constructor chaining - in order to build a house A dwelling must be built first
// therefore every constructor must build a dwelling as its first line of code
// that is done with the super keyword
public class House extends Dwelling {
	
	int walls;

	House(int area, String owner, int walls){
		super(area,owner); // very first line in children constructors must create the parent
		this.walls = walls;
		System.out.println("Finished building a House");
	}
	
	House(){
		super();// Java will implicity put a no args super as the first line
	}
	
	
	House(String owner){
		this(1000,owner,12);
	}

}
