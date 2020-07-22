package dev.nauman.app;

import dev.nauman.controllers.AccountController;
import dev.nauman.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {

		CustomerController.setUp();
		Javalin app = Javalin.create().start(7000);

		//endpoint: "/customers"

		app.post("/customers", CustomerController.createCustomer);//Create customer

		app.get("/customers", CustomerController.getAllCustomers); //get all customers
		app.get("/customers/:cId", CustomerController.getCustomerByCId); //get customer by cId

		app.put("/customers", CustomerController.updateCustomer);//Update customer
		app.put("/customers/:cId/username", CustomerController.changeUsername);//Update customer username 
		app.put("/customers/:cId/password", CustomerController.changePassword);//Update customer password

		app.delete("/customers/:cId", CustomerController.deleteCustomerByCId);//Delete customer

		//*********************************************************************************************************************************************************
		// endpoint: "/customers/:cId/accounts"

		app.post("/customers/:cId/accounts", AccountController.createCustomersAccount); //create an account for a specific user

		app.get("/customers/:cId/accounts", AccountController.getAllCustomerAccounts); //get all accounts for a particular user
		app.get("/customers/:cId/accounts/:aId", AccountController.getCustomerAccountByAId); //get all specific account for a particular user

		app.put("/customers/:cId/accounts", AccountController.updateCustomersAccount); //update a customers account
		app.put("/customers/:cId/accounts/:aId/deposit",AccountController.depositIntoCustomersAccount); //deposit into customers account
		app.put("/customers/:cId/accounts/:aId/withdraw",AccountController.withdrawFromCustomersAccount); //withdraw out of customers account

		app.delete("/customers/:cId/accounts/:aId", AccountController.deleteCustomerAccountByAId); //delete customers account by aId
		app.delete("/customers/:cId/accounts/", AccountController.deleteAccountsByCId); //delete customers account(s) by cId

		//*********************************************************************************************************************************************************
		//endpoint: "/accounts"

		app.post("/accounts", AccountController.createAccount);//Create account

		app.get("/accounts", AccountController.getAllAccounts);//Get all accounts
		app.get("/accounts/:aId", AccountController.getAccountByAId);//get account by id

		app.put("/accounts", AccountController.updateAccount); //update any account
		app.put("/accounts/:aId/deposit", AccountController.depositIntoAccount);//deposit into any account
		app.put("/accounts/:aId/withdraw", AccountController.withdrawFromAccount);//withdraw out of any account

		app.delete("/accounts/:aId", AccountController.deleteAccountByAId); //delete any account by aId
	}
}
