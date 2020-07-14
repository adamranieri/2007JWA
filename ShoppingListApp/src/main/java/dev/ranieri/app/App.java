package dev.ranieri.app;

import dev.ranieri.controllers.ShoppingListController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// get designed to get information
		app.get("/shoppinglist", ShoppingListController.getAllItems);
		
		// posts are designed to create information
		app.post("/additem", ShoppingListController.addToList);
		
	}

}
