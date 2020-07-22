package dev.cosentino.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.cosentino.resources.Account;
import dev.cosentino.resources.Customer;
import dev.cosentino.services.AccountService;
import dev.cosentino.services.AccountServiceImpl;
import dev.cosentino.services.CustomerService;
import dev.cosentino.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccountsFromCustomerId = (ctx)->{
		int customerId = Integer.parseInt(ctx.pathParam("customer_id"));
		Customer customer = cserv.getCustomerById(customerId);
		Set<Account> accounts = aserv.getAccountsByCustomerId(customerId);
		
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		String balanceLessThan = ctx.queryParam("balancelessthan");
		
		if(balanceGreaterThan != null && balanceLessThan != null) {
			Set<Account> between = aserv.getBalanceBetween(customer, Float.parseFloat(balanceLessThan), Float.parseFloat(balanceGreaterThan));
			ctx.result(gson.toJson(between));
		}
		else if(balanceGreaterThan != null) {
			Set<Account> accountsGreater = aserv.getBalanceGreaterThan(customer, Float.parseFloat(balanceGreaterThan));
			ctx.result(gson.toJson(accountsGreater));
		}
		else if(balanceLessThan != null) {
			Set<Account> accountsLess = aserv.getBalanceLessThan(customer, Float.parseFloat(balanceLessThan));
			ctx.result(gson.toJson(accountsLess));
		}
		else if(balanceGreaterThan == null && balanceLessThan == null) {
			ctx.result(gson.toJson(accounts));
		}
		ctx.status(200);
		if(customer == null) {
			ctx.status(404);
		}
	};
	
	public static Handler getAccountFromAccountId = (ctx)->{
		int accountId = Integer.parseInt(ctx.pathParam("account_id"));
		Account account = aserv.getAccountByAccountId(accountId);
		
		if(account == null)
			ctx.status(404);
		else {
			ctx.status(200);
			ctx.result(gson.toJson(account));
		}
	};
	
	public static Handler createAccount = (ctx)->{
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		
		int custId = Integer.parseInt(ctx.pathParam("customer_id"));
		Customer customer = cserv.getCustomerById(custId);
		if(customer != null) {
			account.setCustomerId(custId);
			aserv.createAccount(account);
			ctx.status(201);
			ctx.result(gson.toJson(account));
		}else {
			ctx.status(404);
		}
	};
	
	public static Handler updateAccountBalance = (ctx)->{
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		
		String update = ctx.queryParam("updatebalance");
		if(update != null && account != null) {
			int custId = Integer.parseInt(ctx.pathParam("customer_id"));
			account.setCustomerId(custId);
			
			int accountId = Integer.parseInt(ctx.pathParam("account_id"));
			account.setAccountId(accountId);
			try {
				aserv.updateBalance(account, Float.parseFloat(update));
				ctx.status(200);
				ctx.result(gson.toJson(account));
			}catch(Exception NegativeBalanceException) {
				ctx.status(404);
			}
		}
		else {
			ctx.status(404);
		}
	};
	
	public static Handler deleteAccount = (ctx)->{
		String id = ctx.pathParam("account_id");
		Boolean result = aserv.deleteAccount(Integer.parseInt(id));
		if(result == true) {
			ctx.status(200);
			ctx.result(result.toString());
		}
		else{
			ctx.status(404);
		}
	};
}
