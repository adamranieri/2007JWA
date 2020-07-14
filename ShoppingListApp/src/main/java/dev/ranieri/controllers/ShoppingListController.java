package dev.ranieri.controllers;

import dev.ranieri.services.ShoppListImpl;
import dev.ranieri.services.ShoppingListService;
import io.javalin.http.Handler;

// A controller is a class that holds all of your handler methods
// the logic in you controller methods should focus on API stuff
// normally does not contain a lot of logic (that is for your services)
public class ShoppingListController {

	public static ShoppingListService slserv =  new ShoppListImpl();
	
	public static Handler addToList = (ctx)->{
		// Whenever you add new information you should use a post
		// The information you are sending should be sent via the body of the http request
		String item = ctx.body();
		slserv.addToShoppingList(item);
		ctx.result("added item to shopping list");
		
	};
	
	
	public static Handler getAllItems = (ctx)->{
		
		String allItems = slserv.getAllItems();
		ctx.result(allItems);
	};
}
