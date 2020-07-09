package b;

import a.Parent;

public class Child extends Parent {
	
	public void childMethod() {
		this.proMethod();// this protected method is inherited and accesible in the child
	}

}
