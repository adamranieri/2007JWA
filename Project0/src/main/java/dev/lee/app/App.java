package dev.lee.app;

import dev.lee.controllers.AccountController;
import dev.lee.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// GET
		// Returns all customers
		app.get("/customers", CustomerController.getAllCustomers);
		// Returns customer of given customer ID.
		app.get("/customers/:cid", CustomerController.getCustomerByCid);
		// Returns all accounts
		app.get("/accounts", AccountController.getAllAccounts);
		// Returns account of given customer ID and account ID
		app.get("/customers/:cid/accounts/:aid", AccountController.getAccountByCidAndAid);
		// Returns account given account ID
		app.get("/accounts/:aid", AccountController.getAccountByAid);
		// Returns all accounts of given customer ID
		app.get("/customers/:cid/accounts", AccountController.getAccountsByCid);
		
		
		// POST
		// Creates customer
		app.post("/customers", CustomerController.createCustomer);
		// Creates account
		app.post("/customers/:cid/accounts", AccountController.createAccount);
		app.post("/accounts", AccountController.createAccount);
		
		
		// PUT
		// Updates customer
		app.put("/customers", CustomerController.updateCustomer);
		// Updates account
		app.put("/accounts", AccountController.updateAccount);
		
		
		// DELETE
		// Deletes customer
		app.delete("/customers/:cid", CustomerController.deleteCustomer);
		// Deletes account
		app.delete("/accounts/:aid", AccountController.deleteAccount);
	}

}
