package dev.rutan.app;

import dev.rutan.controllers.AccountController;
import dev.rutan.controllers.CustomerController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		//create
		app.post("/customers", CustomerController.createCustomer);
		app.post("/customers/:cId/accounts", AccountController.createAccount);

		//read
		app.get("/customers", CustomerController.getAllCustomers);
		app.get("/customers/:cId", CustomerController.getCustomerById);
		app.get("/customers/:cId/accounts", AccountController.getAllAccounts);
		app.get("/customers/:cId/accounts/:aId", AccountController.getAccountById);
		
		//update
		app.put("/customers", CustomerController.updateCustomer);
		app.put("/customers/:cId/accounts", AccountController.updateAccount);
		
		//delete
		app.delete("/customers/:cId", CustomerController.deleteCustomer);
		app.delete("/customers/:cId/accounts/:aId", AccountController.deleteAccount);
		
	}

}
