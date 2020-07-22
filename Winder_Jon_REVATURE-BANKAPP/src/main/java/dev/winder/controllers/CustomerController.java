package dev.winder.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.winder.entities.BankAccount;
import dev.winder.entities.Customer;
import dev.winder.services.CustomerService;
import dev.winder.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	
	private static CustomerService cserv = new CustomerServiceImpl();
	
	//invokes object to json
	private static Gson gson = new Gson();
	
	
	//Create
	public static Handler createCustomer = (ctx)-> {
		
			//we only need the ctx body to create our customer.
			String customerJson = ctx.body();
			Customer customer = gson.fromJson(customerJson, Customer.class); // transform the json into a Shcool object
			cserv.createNewCustomer(customer);
			ctx.status(201); // 201 is the status code to return if you successfully add something
			//usually you want to return the created object
			ctx.result(gson.toJson(customer));
		};
	
		//Read
	public static Handler getCustomerByCustomerId = (ctx)->{
		int customerId = Integer.parseInt(ctx.pathParam("id"));
		Customer customer = cserv.getCustomerByCustomerId(customerId);
		String json = gson.toJson(customer);
		ctx.result(json);
		
	};
	

	
	public static Handler getCustomerWithUsername = (ctx) ->{
		String userName= ctx.queryParam("username");		
		Customer customer = cserv.getCustomerByUsername(userName);
		String json = gson.toJson(customer.getCustomerId());
		ctx.result(json);
			
	};
	
	
	//Update
	public static Handler updateCustomerInfo = (ctx)->{
		Customer customer = gson.fromJson(ctx.body(), Customer.class);
		cserv.updateCustomerInfo(customer);
		String json = gson.toJson(customer);
		ctx.result(json);
		
	};
	
	//Read
	public static Handler getAllCustomers = (ctx)->{
		Set<Customer>customers = cserv.getAllCustomersInBranch();
		String json = gson.toJson(customers);
		ctx.result(json);
		
	};
	
	//Delete
	public static Handler deleteCustomerByCid = (ctx)->{
		
		int customerId = Integer.parseInt(ctx.pathParam("id"));
		Boolean result = cserv.deleteCustomerById(customerId);
		ctx.result("Your customer profile being deleted has been set to " + result.toString());

		
		};
	
	
	
		
	}


	
	
	
	
	


