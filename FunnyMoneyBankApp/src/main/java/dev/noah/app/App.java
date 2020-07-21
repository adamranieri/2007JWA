package dev.noah.app;

import dev.noah.controllers.AccountController;
import dev.noah.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		//Create
		app.post("/customers", CustomerController.createCustomer); // create a customer
		app.post("/accounts", AccountController.createAccount); //create an account
		
		
		//Read
		app.get("/customers/:cid", CustomerController.getCustomerById); // get a customer
		app.get("/customers", CustomerController.getAllCustomers); // get all customers
		app.get("/customers/:cid/accounts", AccountController.getAllCustomerAccountsById);
		app.get("/customers/:cid/accounts/:aid", AccountController.getAccount); // get a customers specified account

		
		//Update
		app.put("/customers", CustomerController.updateCustomer); // update a customer
		app.put("/customers/:cid/accounts", AccountController.updateAccount); // update an account
		
		
		
		//Destroy
		app.delete("/customers/:cid", CustomerController.deleteCustomer); // delete a customer
		app.delete("/customers/:cid/accounts/:aid", AccountController.deleteAccount); // delete an account
		
		
	} 
	
	
}
