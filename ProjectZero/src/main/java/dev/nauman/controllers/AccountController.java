package dev.nauman.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.nauman.entities.Account;
import dev.nauman.services.AccountService;
import dev.nauman.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	private static AccountService aserv = new AccountServiceImpl();
	private static Gson gson = new Gson();

	// "/accounts" methods 
	public static Handler createAccount = (ctx) ->{
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account = aserv.createAccount(account);
		accountJson = gson.toJson(account);
		ctx.result(accountJson);
		ctx.status(201);
	};
	public static Handler getAllAccounts = (ctx) ->{
		String json;
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		String balanceLessThan = ctx.queryParam("balancelessthan");

		if(balanceGreaterThan == null && balanceLessThan == null) {			 // no query parameter
			Set<Account> accounts = aserv.getAllAccounts();
			json = gson.toJson(accounts);
			ctx.result(json);	
		} else if(balanceGreaterThan != null && balanceLessThan == null){ 	// greater than query parameter used
			Set<Account> accounts = aserv.getAllAccountsWithBalanceGreaterThan(Double.parseDouble(balanceGreaterThan));
			json = gson.toJson(accounts);
			ctx.result(json);
		} else if(balanceGreaterThan == null && balanceLessThan != null){ 	// less than query parameter used
			Set<Account> accounts = aserv.getAllAccountsWithBalanceLessThan(Double.parseDouble(balanceLessThan));
			json = gson.toJson(accounts);
			ctx.result(json);
		}else { 															// both query parameters are used
			Set<Account> accountsGreater = aserv.getAllAccountsWithBalanceGreaterThan(Double.parseDouble(balanceGreaterThan));
			Set<Account> accountsLesser = aserv.getAllAccountsWithBalanceLessThan(Double.parseDouble(balanceLessThan));
			Set<Account> accounts = new HashSet<Account>();

			for(Account accountGreater : accountsGreater) {
				for(Account accountLesser : accountsLesser) {
					if(accountGreater.equals(accountLesser)) {
						accounts.add(accountGreater);
						break;
					}
				}
			}
			json = gson.toJson(accounts);
			ctx.result(json);
		}
	};
	public static Handler getAccountByAId = (ctx) ->{
		String id = ctx.pathParam("aId");
		Account account = aserv.getAccountByAId(Integer.parseInt(id));
		ctx.result(gson.toJson(account));
	};
	public static Handler updateAccount = (ctx) ->{
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account = aserv.updateAccount(account);
		accountJson = gson.toJson(account);
		ctx.result(accountJson);
	};
	public static Handler depositIntoAccount = (ctx) ->{
		int aId = Integer.parseInt(ctx.pathParam("aId"));
		double amount = Double.parseDouble(ctx.body());
		Account account = aserv.deposit(aserv.getAccountByAId(aId), amount);
		ctx.result(gson.toJson(account));
	};
	public static Handler withdrawFromAccount = (ctx) ->{
		int aId = Integer.parseInt(ctx.pathParam("aId"));
		double amount = Double.parseDouble(ctx.body());
		Account account = aserv.withdraw(aserv.getAccountByAId(aId), amount);
		ctx.result(gson.toJson(account));
	};
	public static Handler deleteAccountByAId = (ctx) ->{
		String id = ctx.pathParam("aId");
		aserv.deleteAccountByAId(Integer.parseInt(id));
	};

	//**************************************************************************************************************************************
	// "/customers/:cId/accounts" methods

	public static Handler createCustomersAccount = (ctx) ->{
		String accountJson = ctx.body();
		String cId = ctx.pathParam("cId");
		Account account = gson.fromJson(accountJson, Account.class);
		account.setcId(Integer.parseInt(cId));
		account = aserv.createAccount(account);
		accountJson = gson.toJson(account);
		ctx.result(accountJson);
	};
	public static Handler getAllCustomerAccounts = (ctx) -> {
		String json;
		String cIdString = ctx.pathParam("cId");
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		String balanceLessThan = ctx.queryParam("balancelessthan");
		int cId = Integer.parseInt(cIdString);

		if(balanceGreaterThan == null && balanceLessThan == null) { 		// no query parameter
			Set<Account> accounts = aserv.getAccountsByCId(cId);
			json = gson.toJson(accounts);
			ctx.result(json);
		} else if(balanceGreaterThan != null && balanceLessThan == null){ 	// greater than query parameter used
			Set<Account> accounts = aserv.getAccountsByCIdWithBalanceGreaterThan(cId, Double.parseDouble(balanceGreaterThan));
			json = gson.toJson(accounts);
			ctx.result(json);
		} else if(balanceGreaterThan == null && balanceLessThan != null){ 	// less than query parameter used
			Set<Account> accounts = aserv.getAccountsByCIdWithBalanceLessThan(cId, Double.parseDouble(balanceLessThan));
			json = gson.toJson(accounts);
			ctx.result(json);
		}else { 															// both query parameters are used
			Set<Account> accountsGreater = aserv.getAccountsByCIdWithBalanceGreaterThan(cId, Double.parseDouble(balanceGreaterThan));
			Set<Account> accountsLesser = aserv.getAccountsByCIdWithBalanceLessThan(cId, Double.parseDouble(balanceLessThan));
			Set<Account> accounts = new HashSet<Account>();
			
			for(Account accountGreater : accountsGreater) {
				for(Account accountLesser : accountsLesser) {
					if(accountGreater.equals(accountLesser)) {
						accounts.add(accountGreater);
						break;
					}
				}
			}
			json = gson.toJson(accounts);
			ctx.result(json);
		}
	};
	public static Handler getCustomerAccountByAId = (ctx) ->{
		String aId = ctx.pathParam("aId");
		String cId = ctx.pathParam("cId");
		Account account = aserv.getCustomerAccountByAId(Integer.parseInt(cId), Integer.parseInt(aId));

		if(account != null) {
			String accountJson = gson.toJson(account);
			ctx.result(accountJson);
			ctx.status(200);
		}else {
			ctx.result("That is an invalid account id for this customer.");
			ctx.status(400);
		}
	};
	public static Handler updateCustomersAccount = (ctx) ->{
		String accountJson = ctx.body();
		String cId = ctx.pathParam("cId");
		Account account = gson.fromJson(accountJson, Account.class);

		if(aserv.customerOwnedAccount(Integer.parseInt(cId), account)) {
			account = aserv.updateAccount(account);
			accountJson = gson.toJson(account);
			ctx.result(accountJson);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
	public static Handler depositIntoCustomersAccount = (ctx) ->{
		String amount = ctx.body();
		String cId = ctx.pathParam("cId");
		String aId = ctx.pathParam("aId");
		Account account = aserv.getAccountByAId(Integer.parseInt(aId));

		if(aserv.customerOwnedAccount(Integer.parseInt(cId), account)) {
			account = aserv.deposit(account, Double.parseDouble(amount));
			String accountJson = gson.toJson(account);
			ctx.result(accountJson);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
	public static Handler withdrawFromCustomersAccount = (ctx) ->{
		String amount = ctx.body();
		String cId = ctx.pathParam("cId");
		String aId = ctx.pathParam("aId");
		Account account = aserv.getAccountByAId(Integer.parseInt(aId));

		if(aserv.customerOwnedAccount(Integer.parseInt(cId), account)) {
			account = aserv.withdraw(account, Double.parseDouble(amount));
			String accountJson = gson.toJson(account);
			ctx.result(accountJson);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
	public static Handler deleteCustomerAccountByAId = (ctx) ->{
		String cId = ctx.pathParam("cId");
		String aId = ctx.pathParam("aId");
		Account account = aserv.getAccountByAId(Integer.parseInt(aId));

		if(aserv.customerOwnedAccount(Integer.parseInt(cId), account)) {
			if(aserv.deleteAccountByAId(Integer.parseInt(aId))) {
				ctx.status(200);
			}else {
				ctx.status(400);
			}
		}else {
			ctx.status(400);
		}

	};
	public static Handler deleteAccountsByCId = (ctx) ->{
		String id = ctx.pathParam("cId");
		aserv.deleteAccountsByCId(Integer.parseInt(id));
	};
}
