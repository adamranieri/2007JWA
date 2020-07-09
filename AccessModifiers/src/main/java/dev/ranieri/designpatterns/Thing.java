package dev.ranieri.designpatterns;

// A singleton design pattern is to ensure you only ever have one instance of a class
// Applies to things like services and database connections
// You only ever want to create a single database connection (multiples serve no purpose and the slow application)
public class Thing {
	
	// the class stores a static copy of itself
	private static Thing thing = null;
	
	// private constructor
	private Thing() {
		System.out.println("Thing created");
	}
	
	// public method responsible for getting us an instance of the class
	// this method has logic in it to ensure it is createdonly once
	public static Thing getThingInstance() {
		
		if(Thing.thing == null) {
			Thing.thing = new Thing();
			return Thing.thing;
		}else {
			return Thing.thing;
		}
		
	}
	
	

}
