package dev.laurent.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.laurent.entities.Account;
import dev.laurent.entities.Customer;
import dev.laurent.services.CustomerService;
import dev.laurent.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	
	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx) ->{
		Set<Customer> customers = cserv.getAllCustomers();				
		String json = gson.toJson(customers);
		ctx.result(json);
		
		String username = ctx.queryParam("username");
		if (username != null) {
			Customer userFound = cserv.getCustomerByUsername(username);
			ctx.result(gson.toJson(userFound));
		} else {	
			ctx.result(json);
		}
	};
	
	public static Handler createCustomer= (ctx) ->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.addCustomer(customer);
		ctx.status(201);
		ctx.result(gson.toJson(customer));
	};
	
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("cId");
		Customer customer = cserv.getCustomerById(Integer.parseInt(id));
		String json = gson.toJson(customer);
		ctx.result(json);	
	};
	
	public static Handler updateCustomer = (ctx)->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.updateCustomer(customer);	
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler deleteCustomer = (ctx) ->{
		String id = ctx.pathParam("cId");
		boolean result = cserv.deleteCustomerById(Integer.parseInt(id));
	};
}
