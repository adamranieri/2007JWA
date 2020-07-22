package dev.zak.controllers;

import com.google.gson.Gson;

import dev.zak.entities.Account;
import dev.zak.entities.AppError;
import dev.zak.entities.Customer;
import dev.zak.services.AccountService;
import dev.zak.services.AccountServiceInterface;
import dev.zak.services.CustomerService;
import dev.zak.services.CustomerServiceInterface;
import io.javalin.http.Handler;

public class AccountHandler {

	public AccountHandler() {
		super();
	}

	static private AccountServiceInterface aServ = new AccountService();
	static private CustomerServiceInterface cServ = new CustomerService();
	static private Gson gson = new Gson();
	static private AppError err;

	private static boolean RequestNotvalid(Account a) {
		// account balance can be 0 
		if(a.getcId()==0 || a.getAccountName() == null) {
			AccountHandler.err = new AppError(1, "Request format problem");
			return true;
		}
		return false;
	}
	private static boolean isNotAccountById(int cid, int aid) {
		Account a = aServ.getAccountById(aid);
		if(a == null) {
			err = new AppError(3, "Account ID not found");
			return true;
		}
		else if(a.getcId() != cid) {
			err = new AppError(7, "Account ID Does not belong to Customer");
			return true;
		}
		return false;
	}
	private static boolean isNotCustomerById(int cid) {
		Customer c = cServ.getCustomerById(cid);
		if(c == null) {
			err = new AppError(3, "Customer ID not found");
			return true;
		}
		return false;
	}
	
	public static Handler createAccount = (ctx)->{
		Account a = gson.fromJson(ctx.body(), Account.class);
		if(a.getcId() != Integer.parseInt(ctx.pathParam(":cid"))) {
			AccountHandler.err = new AppError(2, "Inconsistent Custommer IDs");
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(400);
		}
		else if(AccountHandler.RequestNotvalid(a)) {
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(400);
		}else {
			a = aServ.createAccount(Integer.parseInt(ctx.pathParam(":cid")), a);
			if(a.getcId() == 0 && a.getaId() == 0.0) {
				AccountHandler.err = new AppError(3, "Custommer IDs not found");
				ctx.result(gson.toJson(AccountHandler.err));
				ctx.status(404);
			}
			else if(a.getaId() == 0 && a.getBalance() == 0) {
				AccountHandler.err = new AppError(6, "Negative value is not allowed: Balance");
				ctx.result(gson.toJson(AccountHandler.err));
				ctx.status(400);
			}
			else {
				String result = gson.toJson(a);
				ctx.result(result);
				ctx.status(201);
			}
		}
	};
	
	public static Handler getAllAccounts = (ctx)->{
		int cid = Integer.parseInt(ctx.pathParam(":cid"));
		if(isNotCustomerById(cid)) {
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(404);
		}
		else {
			String result;
			String balancelessthan = ctx.queryParam("balancelessthan");
			String balancegreaterthan= ctx.queryParam("balancegreaterthan");
			if(balancelessthan != null && balancegreaterthan != null) {
				result = gson.toJson(aServ.getAccountBalanceBtween(cid, Integer.parseInt(balancegreaterthan), Integer.parseInt(balancelessthan)));
			}
			else if(balancelessthan != null) {
				result = gson.toJson(aServ.getAccountLessThan(cid, Integer.parseInt(balancelessthan)));
			}
			else if(balancegreaterthan != null) {
				result = gson.toJson(aServ.getAccountGreaterThan(cid, Integer.parseInt(balancegreaterthan)));
			}
			else {
				result = gson.toJson(aServ.getAllAccountsByCustomerId(cid));
			}
			ctx.result(result);
		}
	};
	
	
	public static Handler getAccountById = (ctx)->{
		int cid = Integer.parseInt(ctx.pathParam(":cid"));
		int aid = Integer.parseInt(ctx.pathParam(":aid"));
		if(isNotCustomerById(cid)) {
			ctx.result(gson.toJson(err));
			ctx.status(404);
		}
		else {
			String result = gson.toJson(aServ.getAccountById(cid,aid));
			if(gson.fromJson(result, Account.class) == null) {
				err = new AppError(3, "Account ID not found");
				ctx.result(gson.toJson(err));
				ctx.status(404);
			}else {
				ctx.result(result);
			}
		}
	};

	public static Handler updateAccount = (ctx)->{
		Account a = gson.fromJson(ctx.body(), Account.class);
		if(a.getcId() != Integer.parseInt(ctx.pathParam(":cid"))) {
			AccountHandler.err = new AppError(2, "Inconsistent Custommer IDs");
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(400);
		}
		else if(a.getaId() != Integer.parseInt(ctx.pathParam(":aid"))) {
			AccountHandler.err = new AppError(2, "Inconsistent Account IDs");
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(400);
		}
		else if(AccountHandler.RequestNotvalid(a)) {
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(400);
		}else if(AccountHandler.isNotCustomerById(Integer.parseInt(ctx.pathParam(":cid")))) {
				ctx.result(gson.toJson(AccountHandler.err));
				ctx.status(404);
		}
		else {
			a = aServ.updateAccount(Integer.parseInt(ctx.pathParam(":cid")), a);
			if(a.getaId() == 0 && a.getBalance() == 0) {
				AccountHandler.err = new AppError(6, "Negative value is not allowed: Balance");
				ctx.result(gson.toJson(AccountHandler.err));
				ctx.status(400);
			}
			else {
				String result = gson.toJson(a);
				ctx.result(result);
				ctx.status(200);
			}
		}
		
		
		
	};
	
	public static Handler deleteAccount = (ctx)->{
		int cid = Integer.parseInt(ctx.pathParam(":cid"));
		int aid = Integer.parseInt(ctx.pathParam(":aid"));
		if(isNotCustomerById(cid)) {
			ctx.result(gson.toJson(err));
			ctx.status(404);
		}
		else if(isNotAccountById(cid, aid)) {
			ctx.result(gson.toJson(err));
			if(err.getErrorCode()==7)
				ctx.status(401);
			else 
				ctx.status(404);
		}
		else {
			boolean isDeleted = aServ.deleteAccountById(cid, aid);
			if(isDeleted) {
				ctx.result("Account Deleted with Success");
			}else {
				AccountHandler.err = new AppError(5, "Account Deletion Problem");
				ctx.result(gson.toJson(AccountHandler.err));
				ctx.status(500);
			}
		}
	};
	


	
	//	/transactions/customerID/accountFrom/accountTo/amount
	public static Handler transferMoney = (ctx)->{
		System.out.println("transferMoney");
		boolean result = aServ.transferMoney(
							Integer.parseInt(
								ctx.pathParam(":aidFrom")
							),
							Integer.parseInt(
								ctx.pathParam(":aidTo")
							),
							Float.parseFloat(
								ctx.pathParam(":amount")
							)
						);
		if(result == true) {
			ctx.result("Transfert is successful");
			ctx.status(200);
		}
		else {
			AccountHandler.err = new AppError(5, "Transfert Problem");
			ctx.result(gson.toJson(AccountHandler.err));
			ctx.status(500);
		}
		
	};

}
