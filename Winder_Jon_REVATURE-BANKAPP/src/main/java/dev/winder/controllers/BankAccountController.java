package dev.winder.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.winder.bankappexceptions.InsufficientFundsException;
import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;
import dev.winder.entities.Transaction;
import dev.winder.services.BankServiceImpl;
import dev.winder.services.BankServices;
import dev.winder.services.CustomerService;
import dev.winder.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class BankAccountController {
	
	public static BankServices bserv = new BankServiceImpl();
	public static CustomerService cserv = new CustomerServiceImpl();
	
	private static Gson gson = new Gson();
	
	
	public static Handler createAccount= (ctx) ->{
	
		String bankAccountJson = ctx.body();
		BankAccount bankAccount = gson.fromJson(bankAccountJson, BankAccount.class); // transform the json into a Shcool object
		bserv.createBankAccount(bankAccount);
		ctx.status(201); 
		ctx.result(gson.toJson(bankAccount));
	};
	
	public static Handler getBankAccountByAid = (ctx)->{
		
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		BankAccount bankAccount = bserv.getBankAccountByAid(accountId);
		String json = gson.toJson(bankAccount);
		ctx.result(json);
		
		
	};
	
	public static Handler getAllBankAccountsInBranch = (ctx) ->{
		
		Set<BankAccount>allaccounts = bserv.getAllBankAccountsInBranch();
		String json = gson.toJson(allaccounts);
		ctx.result(json);
		
	};
	
	public static Handler getAllCurrentOfCurrentCustomersAccounts = (ctx) ->{
		
		int customerId = Integer.parseInt(ctx.pathParam("cid"));
		Set<BankAccount> bankaccounts = bserv.getAllCurrentCustomerAccounts(customerId);
		Set<BankAccount>toJson = new HashSet<BankAccount>();
		for(BankAccount bankaccount: bankaccounts) {
			
			if(bankaccount.getCustomerId() == customerId) {
				toJson.add(bankaccount);
			}
		}
	
		String accountsJson = gson.toJson(toJson);
		ctx.result(accountsJson);
		
	};
	
	//only account name
	public static Handler updateBankAccountName = (ctx)-> {
		
		
		BankAccount toJson = new BankAccount();
		BankAccount bankAccount = gson.fromJson(ctx.body(), BankAccount.class);
		toJson = bserv.updateBankAccountName(bankAccount, bankAccount.getAccountName());
		String json = gson.toJson("Your name was changed to " + toJson.getAccountName());
		ctx.result(json);
		
	};
	
	public static Handler getBankAccountBalanceByAid = (ctx)->{
		
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		BankAccount bankAccount = bserv.returnBalanceByAid(accountId);
		String json = gson.toJson(bankAccount.getAccountBalance());
		ctx.result(json);
		
	};
	
	public static Handler deleteBankAccountByAid = (ctx) ->{
		
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		boolean result = bserv.deleteBankAccount(accountId);
		
		if(result == true) {
			ctx.result("Your account with id " + accountId + " has been deleted.");
		}else {
			ctx.result("This account never did exist");
		}
	
	};
	
	//removes every account and customer account related with cid
	public static Handler removeEveryAccountForCustomer = (ctx) ->{
		
		int customerId = Integer.parseInt(ctx.pathParam("cid"));
		boolean result = bserv.removeThisCustomersAccounts(customerId);
		cserv.deleteCustomerById(customerId);
		
		if(result == true) {
			ctx.result("Your customer and bank  accounts related with cid " + customerId + " has been removed from our database");
		}else {
			ctx.result("This account never existed");
		}
		
		
	};
	
	public static Handler depositFunds = (ctx)->{
		
		String transactionJson = ctx.body();
		
		Transaction transaction = gson.fromJson(transactionJson, Transaction.class);
		double deposit = (transaction.getFinalBalance() - transaction.getPrevBalance());
		bserv.depositFunds(transaction, deposit);
		
		if(deposit > 0) {
			ctx.status(201);
			ctx.result(gson.toJson(transaction));
		}
		
		
	};
	
	public static Handler withdrawalFunds = (ctx) ->{
		
		String transactionJson = ctx.body();
		
		Transaction transaction = gson.fromJson(transactionJson, Transaction.class);
		double withdrawal = (transaction.getPrevBalance() - transaction.getFinalBalance());
		
		if(withdrawal < transaction.getPrevBalance()) {
			ctx.status(201);
			ctx.result(gson.toJson(transaction));
			
		}
		
		
	};
	
	
	
	

	
		

}
