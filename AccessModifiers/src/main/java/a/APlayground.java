package a;

public class APlayground {
	
	// Access modifiers
	// public
	// protected
	// default - no keyword
	// private	
	
	
	// Access modifiers allow us to protect or encapsulate certain parts of our code from other parts of our
	// code. 
	// We want to force developers to use the public methods and variables in our class
	// this Limit access to data that they should not manuplate directly
	// prevent people from calling a method that should not be used outside of a few circumstance with that class
	
	
	public static void main(String[] args) {
		
		Parent p = new Parent();
		p.pubMethod();
		p.defMethod();
		p.proMethod();
		//p.privMethod(); // not possible not in the same class
		p.pubMethodCallsPrivMethod();
		
		
		
		
	}

}
