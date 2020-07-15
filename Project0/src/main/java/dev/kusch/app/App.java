package dev.kusch.app;

import dev.kusch.controllers.AccountControllers;
import dev.kusch.controllers.CustomerControllers;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// REST APIs should support, at a minimum, the basic CRUD operations
		
		// read operations
		app.get("/customers", CustomerControllers.getAllCustomers);
		app.get("/customers/:cid", CustomerControllers.getCustomerById);
		app.get("/customers/:cid/accoutns", AccountControllers.getAllAccounts);
		app.get("/customers/:cid/accounts/:aid", AccountControllers.getAccountById);
		
		// create operation
		app.post("/customers", CustomerControllers.createCustomer);
		app.post("/customers/:cid/accounts", AccountControllers.createAccount);
		
		// update operation
		app.put("/customers/:cid", CustomerControllers.updateCustomer);
		app.put("/customers/:cid/accounts/:aid", AccountControllers.updateAccount);
		
		// delete operation
		app.delete("/customers/:cid", CustomerControllers.deleteCustomer);
		app.delete("/customers/:cid/accounts/:aid", AccountControllers.deleteAccount);
		
	}
}
