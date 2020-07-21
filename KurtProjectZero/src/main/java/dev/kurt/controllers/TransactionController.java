package dev.kurt.controllers;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.Gson;

import dev.kurt.entities.Account;
import dev.kurt.entities.Transaction;
import dev.kurt.services.AccountService;
import dev.kurt.services.AccountServiceImpl;
import dev.kurt.services.TransactionService;
import dev.kurt.services.TransactionServiceImpl;
import io.javalin.http.Handler;

public class TransactionController {

	private static AccountService accServ = new AccountServiceImpl();
	private static TransactionService traServ = new TransactionServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler createTransaction = (ctx)->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));	
		Transaction transaction = gson.fromJson(ctx.body(), Transaction.class); 
		Account account = accServ.getAccountById(accountId);
		
		String addQ = ctx.queryParam("addbalance");
		String subQ = ctx.queryParam("subtractbalance");
		transaction.setaId(accountId);
		transaction.setPrevBalance(account.getAccountBalance());
		
		switch ((addQ != null) + "-" + (subQ != null)) {
	    case "false-false":
			accServ.updateAccountByTransaction(account, transaction);
			traServ.createTransaction(transaction);
			
			ctx.status(201);
			ctx.result(gson.toJson(transaction));	
	        break;
	    case "false-true":
	    	transaction.setFinalBalance(transaction.getPrevBalance() - Double.parseDouble(subQ));
	    	
			accServ.updateAccountByTransaction(account, transaction);
			traServ.createTransaction(transaction);
			
			
			ctx.status(201);
			ctx.result(gson.toJson(transaction));	
	        break;
	    case "true-false":
	    	transaction.setFinalBalance(transaction.getPrevBalance() + Double.parseDouble(addQ));
	    	
			accServ.updateAccountByTransaction(account, transaction);
			traServ.createTransaction(transaction);
			
			
			ctx.status(201);
			ctx.result(gson.toJson(transaction));	
	    	break;
	    case "true-true":
	    	throw new RuntimeException("Only one transaction can be created at a time");
	    default:
	        throw new RuntimeException("This should be impossible!");
		}
	};
	
	
	public static Handler getTransactionById = (ctx)->{
		int transactionId = Integer.parseInt(ctx.pathParam("tid"));
		Transaction transaction = traServ.getTransactionById(transactionId);
		String json = gson.toJson(transaction);
		
		ctx.result(json);
	};
	
	public static Handler getTransactionsByAccountId = (ctx) ->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));	
		ArrayList<Transaction> transactions = traServ.getTransactionsByAccountId(accountId);				
		String json = gson.toJson(transactions);	
		ctx.result(json);
	};
	
	
	public static Handler getAllTransactions = (ctx) ->{
		ArrayList<Transaction> transactions = traServ.getAllTransactions();				
		String json = gson.toJson(transactions);	
		ctx.result(json);
	};
	
	public static Handler updateTransaction = (ctx) ->{
		Transaction transaction = gson.fromJson(ctx.body(), Transaction.class);
		traServ.updateTransaction(transaction);
		
		
		String json = gson.toJson(transaction);
		ctx.result(json);	
		};
	
}
