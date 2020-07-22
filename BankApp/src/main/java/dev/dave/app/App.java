package dev.dave.app;

import dev.dave.controllers.AccountsController;
import dev.dave.controllers.CustomersController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		// CREATE
		
		app.post("/customers", CustomersController.createCustomer); // create customer
		
		app.post("/customers/:cid/accounts", AccountsController.createAccount); // create account
		
		// READ
		
		app.get("/customers/:cid", CustomersController.getCustomerByID); // get customer by cID
		
		app.get("/accounts/:aid", AccountsController.getAccountByID); // get account by account ID
		
		app.get("/customers/:cid/accounts", AccountsController.getAccountByUserID); // get all accounts under a certain customer
		
		
		// UPDATE
		
		app.put("/customers/:cid", CustomersController.updateCustomer); // update customer
		
		app.put("/customers/accounts/:aid", AccountsController.updateAccount); // update account
		
		
		// DELETE
		
		app.delete("/customers/:cid", CustomersController.deleteCustomer); // delete customer
		
		app.delete("/accounts/:aid", AccountsController.deleteAccount); // delete account
		
		app.delete("/customers/:cid/accounts", AccountsController.deleteAllCustAccounts); // delete all accounts under a certain customer
		
		// QUERY PARAMS REQUESTS
		
		app.get("/customers/:username", CustomersController.getCustByName); // to retrieve customer by username
		
		//app.get("/accounts/:balance"); // to retrieve accounts with balance less than 5000 and greater than 2000
		
	}
}
