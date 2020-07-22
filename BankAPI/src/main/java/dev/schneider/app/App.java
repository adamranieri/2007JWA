package dev.schneider.app;

import dev.schneider.controllers.AccountController;
import dev.schneider.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);

		//customer 
		app.post("/customers", CustomerController.createCustomer); 
		app.get("/customers", CustomerController.getAllCustomers); 
		app.get("/customers/:id", CustomerController.getCustomerByID); 
		app.put("/customers", CustomerController.updateCustomer); 
		app.delete("/customers/:id", CustomerController.deleteCustomer);
		
		
		//account
		app.post("/customers/:id/accounts", AccountController.createAccount); 
		app.get("/customers/:id/accounts", AccountController.getAllCustomerAccounts); 
		app.get("/customers/:id/accounts/:aID", AccountController.getAccountByID); 
		app.put("/customers/:id/accounts/:aID", AccountController.updateAccount);
		app.delete("/customers/:id/accounts/:aID", AccountController.deleteAccount);
		
	}
}
