package dev.noah.app;

import dev.noah.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		//Create
		app.post("/customers", CustomerController.createCustomer); // create a customer
		
		//Read
		app.get("/customers/:cid", CustomerController.getCustomerById); // get a customer
		app.get("/customers", CustomerController.getAllCustomers); // get all customers
		
		//Update
		app.put("/customers", CustomerController.updateCustomer); // update a customer
		
		//Destroy
		app.delete("/customers/:cid", CustomerController.deleteCustomer); // delete a customer
	}
	
	
}
