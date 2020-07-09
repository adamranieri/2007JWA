package dev.ranieri.scopes;

public class ScopePlayground {
	
	// Java has 4 scopes
	// static/class - the variable is attached to the class not to the object 
	// Instance/object - the variable is attached to an instance of the object
	// Method scope - The variable is passed a parameter to a method and available in the entire method
	// local - Within some curly brackets in a piece of code
	
	
	// Java DOES NOT HAVE A GLOBAL SCOPE
	
	public static void main(String[] args) {
		// getting static values
		System.out.println(Box.description);
		
		System.out.println(Box.staticValue);		
		Box.staticValue = 87;
		System.out.println(Box.staticValue);
		
		Box a = new Box();
		Box b = new Box();
		
		a.instanceValue = 1;
		b.instanceValue = 2;
		
		System.out.println(a.instanceValue);
		System.out.println(b.instanceValue);
		
		// bad practice
		// you can access static fields through an object but it is discouraged
		System.out.println(a.staticValue);
		System.out.println(b.staticValue);
		
	}

}
