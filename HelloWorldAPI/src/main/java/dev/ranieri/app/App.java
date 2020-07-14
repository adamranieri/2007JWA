package dev.ranieri.app;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class App {
	
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// first parameter is a URI (uniform resource identifier) path that you will serch for in your browser
		// Second parameter is your handler function. A lambda with code that will be called when 
		// you make a request to /hello 
		
		Handler helloHandler = (ctx) ->{
			//ctx is a context object
			// it contains all the information about the request and response
			
			ctx.result("Hello Person");
		};
		
		app.get("/hello", helloHandler);
		
		// name is a path parameter
		// it is not a fixed value. people will put in a value when they make a request
		
		Handler nameHandler = (ctx) ->{
			String name = ctx.pathParam("name");
			System.out.println("Hello " + name);
			ctx.result("Welcome " + name );
			
		};
		
		app.get("/hello/:name",nameHandler);
		
	}

}
