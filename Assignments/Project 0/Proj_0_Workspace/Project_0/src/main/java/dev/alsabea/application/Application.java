package dev.alsabea.application;


import dev.alsabea.controllers.CustomersController;
import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		app.get("/customer", CustomersController.retrieveAllCustomers);
		
		app.post("/customer", CustomersController.addACustomer);
		
		app.put("/customer",CustomersController.updateCustomer );
		
		app.delete("/customer/:cid", CustomersController.deleteCustomer);
		
	}
	
}
