package dev.kyle.app;

import dev.kyle.controllers.AccountController;
import dev.kyle.controllers.CustomerController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		
		app.post("/customers", CustomerController.createCustomer); // Create Customer \
		app.post("/customers/:cid/accounts", AccountController.createAccount); // Create Customer Account \
		
		
		app.get("/customers", CustomerController.getAllCustomers); // \
		app.get("/customers/:cid", CustomerController.getCustomerById); // \
		app.get("/customers/:cid/accounts", AccountController.getAllAccounts); // \
		app.get("/customers/:cid/accounts/:aid", AccountController.getAccountById); // \
		app.get("/accounts/:aid", AccountController.getAccountById); // \
		
		app.put("/customers", CustomerController.updateCustomer);// \
		app.put("/customers/:cid/accounts", AccountController.updateAccount); // \
		
		app.delete("/customers/:cid", CustomerController.deleteCustomer);// \
		app.delete("/customers/:cid/accounts/:aid", AccountController.deleteAccount); // \
		
	}
}
