package dev.atan.controllers;

import java.util.List;
import com.google.gson.Gson;
import dev.atan.entities.Account;
import dev.atan.services.AccountService;
import dev.atan.services.AccountServiceImpl;
import dev.atan.services.CustomerService;
import dev.atan.services.CustomerServiceImpl;
import io.javalin.http.Handler;
import dev.atan.exceptions.*;

public class AccountControllers {
	
	public static AccountService aserv = new AccountServiceImpl();
	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccounts = (ctx) ->{
		List<Account> accounts = aserv.getAllAccounts();		
		String json = gson.toJson(accounts);
		ctx.result(json);
		
		/* String userQ1 = ctx.queryParam("great");
		//String userQ2 = ctx.queryParam("less");
		
		if(userQ2.equals(null)) {
			List<Account> accountByBalance = aserv.getAccountsByBalanceGreaterThan(Integer.parseInt(userQ1));
			ctx.result(gson.toJson(accountByBalance));
		} 
		if(userQ1 != null) {
			List<Account> accountByBalance = aserv.getAccountsByBalanceGreaterThan(Integer.parseInt(userQ1));
			ctx.result(gson.toJson(accountByBalance));
		}
		else if(!userQ1.equals(null) && !userQ2.equals(null)) {
			List<Account> accountByBalance1 = aserv.getAccountsByBalanceLessThan(Integer.parseInt(userQ2));
			for(Account account : accountByBalance1) {
				if(account.getaBalance() < Integer.parseInt(userQ1)) {
					accountByBalance1.remove(account);
				}
			}
			
			ctx.result(gson.toJson(accountByBalance1));
		} 
		else {
			ctx.result(json);
		} */
			
	};
	
	
	public static Handler createAccount = (ctx) ->{
		String accountJSON = ctx.body();
		Account account = gson.fromJson(accountJSON, Account.class);
		
		if(cserv.getCustomerById(account.getcID()) == null){
			ctx.status(404);
			ctx.result("There is no Customer with this ID");
		} else { 
		
		try{aserv.createAccount(account);
		ctx.result(gson.toJson(account));
		ctx.status(201);
	}
	
	catch(NegativeBalance e){
		ctx.status(400);
		ctx.result("Cannot have a negative balance");
	}}
	};
	
	
	public static Handler getAccountById = (ctx)->{
		String id = ctx.pathParam("aID");
		Account account = aserv.getAccountById(Integer.parseInt(id));
		
		if(account == null) {
			ctx.status(404);
			ctx.result("There is no Account with this ID");}
		else {
		
		if(cserv.getCustomerById(account.getcID()) == null){
			ctx.status(404);
			ctx.result("There is no Customer with this ID");
		} 		
			else {
			String json = gson.toJson(account);
			ctx.result(json);		
		}  
		}
	};
	
	public static Handler getAccountByCustomerID = (ctx)->{
		String id = ctx.pathParam("cID");
		List<Account> accounts = aserv.getAccountsByCustomerId(Integer.parseInt(id));
		
		 if(accounts == null) {
			ctx.status(404);
			ctx.result("There is no Accounts for this Customer");
		}
		 else if(cserv.getCustomerById(Integer.parseInt(id)) == null){
				ctx.status(404);
				ctx.result("There is no Customer with this ID");}
		 else {
			String json = gson.toJson(accounts);
			ctx.result(json);		
		}  
		
	};
	
	public static Handler updateAccount = (ctx)->{
		String accountJSON = ctx.body();
		Account account = gson.fromJson(accountJSON, Account.class);
		
		
		if(aserv.getAccountById(account.getaID()) == null){
			ctx.status(404);
			ctx.result("There is no Account with this ID");
		} 
		else if(cserv.getCustomerById(account.getcID()) == null){
				ctx.status(404);
				ctx.result("There is no Customer with this ID");}
		else {	
		
		try{aserv.updateAccount(account);
			ctx.result(gson.toJson(account));
		}
		
		catch(NegativeBalance e){
			ctx.status(400);
			ctx.result("Cannot have a negative balance");
		}
		}
	};
	
	public static Handler deleteAccount = (ctx) ->{
		String id = ctx.pathParam("aID");
		Boolean result = aserv.deleteAccountById(Integer.parseInt(id));
		if(result == false) {
			ctx.status(404);
			ctx.result("There is no Account with this ID");
		}else {
			ctx.result(result.toString());
		} 
		
	};


}
