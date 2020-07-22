package dev.winder.app;

import dev.winder.controllers.BankAccountController;
import io.javalin.Javalin;

public class BankApp {

	public static void main(String[] args) {

		Javalin app = Javalin.create().start(7000);
		
		
		
		app.post("/bankaccounts", BankAccountController.createAccount);
		
		app.post("/transaction", BankAccountController.withdrawalFunds);
		
		app.post("transactions", BankAccountController.depositFunds);
		//READ
		app.get("/bankaccount/:aid",BankAccountController.getBankAccountByAid);
		app.get("/bankaccounts", BankAccountController.getAllBankAccountsInBranch);
		
		app.get("/bankaccounts/:cid",BankAccountController.getAllCurrentOfCurrentCustomersAccounts);
		
		app.get("/bankaccounts/:cid/:aid", BankAccountController.getBankAccountBalanceByAid);
		
		
		app.put("/bankaccounts", BankAccountController.updateBankAccountName);
		
		app.delete("/bankaccount/:aid",BankAccountController.deleteBankAccountByAid);
		
		app.delete("/bankaccounts/:cid", BankAccountController.removeEveryAccountForCustomer);


}
	
}