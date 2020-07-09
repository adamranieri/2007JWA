package a;

public class Parent {
	
	
	// accessible anywhere in the application
	public void pubMethod() {
		System.out.println("This is a public method");
	}
	
	// default + children that inherit from this class in another package
	// Parent protects the child
	protected void proMethod() {
		System.out.println("This is a protected method");
	}
	
	// available anywhere in the same package
	void defMethod() {
		System.out.println("This is a Default method");
	}
	
	// available only in this class
	private void privMethod() {
		System.out.println("This is a private method");
	}
	
	
	//if you want to try to access a private method outside of the class you need another method
	// with a less restrictive modifier
	
	public void pubMethodCallsPrivMethod() {
		System.out.println("Public method that calls on a private method");
		privMethod();
	}
	
	// private methods are often times helper methods

}
