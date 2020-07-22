package dev.winder.app;

import dev.winder.controllers.BankAccountController;
import dev.winder.controllers.CustomerController;
import io.javalin.Javalin;

public class CustomerApp {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		//create
		app.post("/customers", CustomerController.createCustomer);
		
		
		//read
		app.get("/customers/:id", CustomerController.getCustomerByCustomerId);
		app.get("/customers", CustomerController.getAllCustomers);
		
		
		app.get("/customers/:id/username", CustomerController.getCustomerWithUsername);
		
		//update
		app.put("/customers/:id", CustomerController.updateCustomerInfo);
		app.delete("/customers/:id", CustomerController.deleteCustomerByCid);

		
		
	}
	

	



	
	
	
	
	
	
	
	
	
	

}
