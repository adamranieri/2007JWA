package dev.alsabea.application;


import dev.alsabea.controllers.AccountsController;
import dev.alsabea.controllers.CustomersController;
import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		//customer requests
		
		app.get("/customers", CustomersController.retrieveAllCustomers);
		
		app.post("/customers", CustomersController.addACustomer);
		
		app.put("/customers/:id",CustomersController.updateCustomer );
		
		app.delete("/customers/:id", CustomersController.deleteCustomer);
		
		
		app.get("customers/:id", CustomersController.retrieveACustomer);
		
		
		// account requests 
		
		app.get("customers/:id/accounts", AccountsController.retrieveAllCustomerAccounts);
		app.get("customers/:id/accounts/:aid", AccountsController.retrieveAnAccount);
		
		app.post("customers/:id/accounts", AccountsController.createAnAccount);
		
		app.delete("customers/:id/accounts/:aid", AccountsController.deleteAnAccount);
		
		app.put("customers/:id/accounts/:aid",  AccountsController.updateAnAccount);
		
	}
	
}