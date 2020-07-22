package dev.atan.app;

import dev.atan.controllers.AccountControllers;
import dev.atan.controllers.CustomerControllers;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);

		//for customer
		//read operation
		//app.get(path, handler)
		app.get("/customers", CustomerControllers.getAllCustomers);
		app.get("/customers/:cID", CustomerControllers.getCustomerById);
		app.get("customers/:userName", CustomerControllers.getCustomerByUserName);
		
		// create operation
		app.post("/customers", CustomerControllers.createCustomer);
			
		//update operation
		app.put("/customers", CustomerControllers.updateCustomer);
				
		//delete operation
		app.delete("/customers/:cID", CustomerControllers.deleteCustomer);
		app.delete("/customers/:userName", CustomerControllers.deleteCustomerByUserName);
		
		//for account
		//read operation
		app.get("/customers/:cID/accounts", AccountControllers.getAccountByCustomerID);
		app.get("/customers/:cID/accounts/:aID", AccountControllers.getAccountById);
		
		// create operation
		app.post("/customers/:cID/accounts", AccountControllers.createAccount);
			
		//update operation
		app.put("/customers/:cID/accounts/:aID", AccountControllers.updateAccount);
		
		//delete operation
		app.delete("/customers/:cID/accounts/:aID", AccountControllers.deleteAccount);
		
			
	}

}
