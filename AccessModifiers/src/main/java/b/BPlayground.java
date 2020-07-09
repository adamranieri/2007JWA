package b;

import a.Parent;

public class BPlayground {

	public static void main(String[] args) {
		
		Parent p = new Parent();
		
//		p.pubMethod();
//		p.pubMethodCallsPrivMethod();
//		
		// cannot call default
		// cannot call priv
		// cannot call protected
		
		Child c = new Child();
		c.childMethod();
		
		
	}

}
