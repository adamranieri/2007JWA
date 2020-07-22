package dev.edwin.contollers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.edwin.entities.Account;
import dev.edwin.entities.Customer;
import dev.edwin.exceptions.NegativeBalanceException;
import dev.edwin.exceptions.NegativeValueException;
import dev.edwin.services.AccountService;
import dev.edwin.services.AccountServiceImp;
import io.javalin.http.Handler;

public class AccountController {
//	Account openNewAccount(Account account);
//	Account getAccountById(int aId);
//	List<Account> getAccountsByCustomerId(int id);
//	List<Account> getAllAccounts();
//	Account updateAccount(Account account);
//	boolean closeAccount(Account account);
//	boolean closeAccount(int aId);
//	Account depositToAccount(Account account, Double amount) throws NegativeValueException;
//	Account withdrawFromAccount(Account account, Double amount) throws NegativeBalanceException;
//	List<Account> transferMoney(Account a1, Account a2, Double amount) throws NegativeBalanceException, NegativeValueException;

	
	private static AccountService aserv = AccountServiceImp.getAccountService();
	private static Gson gson = new Gson();
	
	public static Handler createNewAccount = (ctx) -> {
		String cid = ctx.pathParam("cid");
		String body = ctx.body();
		Account account = gson.fromJson(body, Account.class);
		
		try
		{
			account.setcId(Integer.parseInt(cid));
			Account result = aserv.openNewAccount(account);
			
			ctx.result(gson.toJson(result));
			ctx.status(201);
		} catch (NumberFormatException e)
		{
			ctx.status(404);
			e.printStackTrace();
		}

	};
	
	public static Handler getAccount = (ctx) -> {
		String id = ctx.pathParam("aid");
		try
		{
			Account account = aserv.getAccountById(Integer.parseInt(id));
			String json = gson.toJson(account);		
			ctx.result(json);	
			ctx.status(200);
		} catch(NumberFormatException e)
		{
			e.printStackTrace();
			ctx.status(404);
		}
	};
	
	public static Handler getAllCustomerAccounts = (ctx) -> {
		String id = ctx.pathParam("cid");
//		This function will get called if they provide cid
//		Can get additional queries made to it:
//		Balance above
//		Balance below
//		Balance between
		
		String greater = ctx.queryParam("balancegreaterthan");
		String less = ctx.queryParam("balancelessthan");
		try
		{
			List<Account> accounts = new ArrayList<Account>();
			if(greater != null && less != null)
			{
				accounts = aserv.getAccountsWithBalanceWithinRange(Integer.parseInt(id), Double.parseDouble(less), Double.parseDouble(greater));
			}
			else if(greater != null )
			{
				accounts = aserv.getAccountsWithBalanceAbove(Integer.parseInt(id), Double.parseDouble(greater));
			}
			else if(less != null)
			{
				accounts = aserv.getAccountsWithBalanceBelow(Integer.parseInt(id), Double.parseDouble(less));
			}
			else
			{
				accounts = aserv.getAccountsByCustomerId(Integer.parseInt(id));
			}
		
		
			
			String json = gson.toJson(accounts);		
			ctx.result(json);	
			ctx.status(200);
		} catch(NumberFormatException e)
		{
			e.printStackTrace();
			ctx.status(404);
		}
	};
	
	public static Handler getAllAccounts = (ctx) -> {
		List<Account> accounts = aserv.getAllAccounts();
		String json = gson.toJson(accounts);
		ctx.result(json);
		
		ctx.status(200);
	};
	
	public static Handler updateAccount = (ctx) -> {
		String cid = ctx.pathParam("cid");
		String body = ctx.body();
		Account received = gson.fromJson(body, Account.class);
		
		try
		{
			List<Account> cAccounts = aserv.getAccountsByCustomerId(Integer.parseInt(cid));
			for(Account in: cAccounts)
			{
				if(in.getaId() == received.getaId())
				{
					if(received.getBalance() >= 0)
					{
						Account account = aserv.updateAccount(received);
						ctx.result(gson.toJson(account));
						ctx.status(201);
						return;
					}
					else
					{
						ctx.result(gson.toJson(null));
						ctx.status(404);
						throw new NegativeBalanceException();
						
					}
						
					
				}
				
			}
			ctx.result(gson.toJson(null));
			ctx.status(404);
		}catch(NumberFormatException e)
		{
			ctx.status(404);
			e.printStackTrace();
			
		}
		
		
				
	};
	
	public static Handler closeAccount = (ctx) -> {
		String id = ctx.pathParam("aid");
		try
		{
			boolean result = aserv.closeAccount(Integer.parseInt(id));
			
			if(result)
				 ctx.status(200);
			 else
				 ctx.status(404);
		}
		catch(NumberFormatException e)
		{
			ctx.status(404);
			e.printStackTrace();
		}
		
	};
	
//	Account depositToAccount(Account account, Double amount) throws NegativeValueException;
//	Account withdrawFromAccount(Account account, Double amount) throws NegativeBalanceException;
//	List<Account> transferMoney(Account a1, Account a2, Double amount) throws NegativeBalanceException, NegativeValueException;

	
	public static Handler makeDeposit = (ctx) -> {
		String aid = ctx.pathParam("aid");
		String amount = ctx.body();
//		System.out.println(gson.fromJson(amount);
		try {
			aserv.depositToAccount(aserv.getAccountById(Integer.parseInt(aid)), Double.parseDouble(amount) );
			
			ctx.status(201);
		} catch (Exception e)
		{
			e.printStackTrace();
			ctx.status(404);
		}
		
	};
	
	public static Handler makeWithdraw = (ctx) -> {
		String aid = ctx.pathParam("aid");
		String amount = ctx.body();
		
		try {
			aserv.withdrawFromAccount(aserv.getAccountById(Integer.parseInt(aid)), Double.parseDouble(amount) );
			
			ctx.status(201);
		} catch (Exception e)
		{
			e.printStackTrace();
			ctx.status(404);
		}
		
	};
	

}
