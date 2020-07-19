package dev.alsabea.application;


import dev.alsabea.controllers.AccountsController;
import dev.alsabea.controllers.CustomersController;
import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		//customer requests
		
		app.get("/customer", CustomersController.retrieveAllCustomers);
		
		app.post("/customer", CustomersController.addACustomer);
		
		app.put("/customer/:id",CustomersController.updateCustomer );
		
		app.delete("/customer/:id", CustomersController.deleteCustomer);
		
		
		app.get("customer/:id", CustomersController.retrieveACustomer);
		
		
		// account requests 
		
		app.get("customer/:id/accounts", AccountsController.retrieveAllCustomerAccounts);
		app.get("customer/:id/accounts/:aid", AccountsController.retrieveAnAccount);
		
		app.post("customer/:id/accounts", AccountsController.createAnAccount);
		
		app.delete("customer/:id/accounts/:aid", AccountsController.deleteAnAccount);
		
		app.put("customer/:id/accounts/:aid",  AccountsController.updateAnAccount);
		
		
		/*
		 * still have the query params for account
		 */
		
	}
	
}