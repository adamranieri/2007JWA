package dev.zak.app;

import dev.zak.controllers.AccountHandler;
import dev.zak.controllers.CustomerHandler;
import io.javalin.Javalin;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// /customers/{cId}/accounts/{aId}

//		app.routes(()->{
//			path("/customers", ()->{
//				get(getAllCustomers);
//				post(null);
//				path("?username=:username", ()->{
//					get(getAllCustomers);
//				});
//				path("/:cid", ()->{
//					get(getCustomerById);
//					put(null);
//					delete(null);
//				});
//			})
//		);
		
		// Customer requests
		app.get("/customers", CustomerHandler.getAllCustomers);
		app.get("/customers/:cid", CustomerHandler.getCustomerById);

		app.post("/customers", CustomerHandler.createCustomer);
		app.put("/customers/:cid", CustomerHandler.updateCustomer);
		app.delete("/customers/:cid", CustomerHandler.deleteCustomer);
		app.delete("/customersWithAccounts/:cid", CustomerHandler.deleteCustomerWithAllAccounts);
		
		// accounts requests
		app.get("/customers/:cid/accounts/", AccountHandler.getAllAccounts);
		app.get("/customers/:cid/accounts/:aid", AccountHandler.getAccountById);

		app.post("/customers/:cid/accounts/", AccountHandler.createAccount);
		app.put("/customers/:cid/accounts/:aid", AccountHandler.updateAccount);
		app.delete("/customers/:cid/accounts/:aid", AccountHandler.deleteAccount);
		
//		/transactions/customerID/accountFrom/accountTo/amount
		app.put("/transactions/:cid/:aidFrom/:aidTo/:amount", AccountHandler.transferMoney);
	}

}
