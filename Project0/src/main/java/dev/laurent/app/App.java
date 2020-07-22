package dev.laurent.app;

import dev.laurent.controllers.CustomerController;
import dev.laurent.controllers.AccountController;
import io.javalin.Javalin;

public class App {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		//CUSTOMER OPERATIONS
		
		// Read Operations
		app.get("/customers", CustomerController.getAllCustomers);	
		app.get("/customers/:cId", CustomerController.getCustomerById);
		
		// Create Operation
		app.post("/customers", CustomerController.createCustomer);
		
		// Update Operation
		app.put("/customers", CustomerController.updateCustomer);
		
		// Delete Operation
		app.delete("/customers/:cId", CustomerController.deleteCustomer);
		
		//ACCOUNT OPERATIONS
		
		// Read Operations
		app.get("/customers/:id/accounts", AccountController.getAllAccountsFromCustomerId);	
		app.get("/customers/:id/accounts/:aid", AccountController.getAccountbyId); //returns a student by id
		app.get("/accounts/:aid", AccountController.getAccountbyId); //also correct
		
		// Create Operations
		app.post("/customers/:id/accounts", AccountController.createAccount); //Add a new student to that school
		
		// Update Operations
		app.put("/customers/:id/accounts", AccountController.updateAccount); //Update a student at that school
		
		// Delete Operations
		app.delete("/customers/:id/accounts/:aid", AccountController.deleteAccount);
		
	}
}
