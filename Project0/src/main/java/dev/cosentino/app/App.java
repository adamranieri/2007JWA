package dev.cosentino.app;

import dev.cosentino.controllers.AccountController;
import dev.cosentino.controllers.CustomerController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);

		// read operations
		app.get("/customers", CustomerController.getAllCustomers); // returns all customers
		app.get("/customers/:customer_id", CustomerController.getCustomerById); // returns specific customer
		
		app.get("/customers/:customer_id/accounts", AccountController.getAllAccountsFromCustomerId);
		app.get("/customers/:customer_id/accounts/:account_id", AccountController.getAccountFromAccountId);
		
		// create operations
		app.post("/customers", CustomerController.createCustomer);
		app.post("/customers/:customer_id/accounts",AccountController.createAccount);
		
		// update operations
		app.put("/customers/:customer_id", CustomerController.updateCustomer);
		app.put("/customers/:customer_id/accounts/:account_id", AccountController.updateAccountBalance);

		// delete operations
		app.delete("/customers/:customer_id", CustomerController.deleteCustomer);
		app.delete("/customers/:customer_id/accounts/:account_id", AccountController.deleteAccount);

	}

}
