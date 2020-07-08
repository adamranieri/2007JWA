package dev.ranieri.constructors;

public class Dwelling {
	
	
	// Dwelling is a small livable place
	
	String owner;
	int area;
	
	Dwelling(int area, String owner){
		// this is necessary because I need to explicitly say that varaibles on
		// the left hand side refer to the instance variables and not the method scoped variables that are passed in
		this.area = area;
// instance variable area = method variable area
		this.owner = owner;
		System.out.println("Finished building a dwelling");
	}
	
	Dwelling(){
		System.out.println("No arguments constroctor");
		this.owner = "no one";
		this.area = 0;
	}

}
