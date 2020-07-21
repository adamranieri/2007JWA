package dev.kurt.app;


import dev.kurt.controllers.CustomerController;
import dev.kurt.controllers.AccountController;
import io.javalin.Javalin;

public class App {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);

		// c 
		app.post("/customers", CustomerController.createCustomer);
		app.post("/customers/:id/accounts", AccountController.createAccount);
		
		// r
		app.get("/customers", CustomerController.getAllCustomers);
		app.get("/customers/:cid", CustomerController.getCustomerById);
		app.get("/customers/:id/accounts", AccountController.getAccountsByCustomerId);
		app.get("/customers/:id/accounts/:aid", AccountController.getAccountById);
		app.get("/accounts/:aid", AccountController.getAccountById); 
		app.get("/accounts", AccountController.getAllAccounts);
		
		
		// u
		app.put("/customers", CustomerController.updateCustomer);
		app.put("/customers/:id/accounts", AccountController.updateAccount);
		
		// d
		app.delete("/customers/:cid", CustomerController.deleteCustomer);
		app.delete("/customers/:id/accounts/:aid", AccountController.deleteAccount);
		
	}
}
