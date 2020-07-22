package dev.cosentino.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.cosentino.resources.Customer;
import dev.cosentino.services.CustomerService;
import dev.cosentino.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {

	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	//return all customers
	public static Handler getAllCustomers = (ctx)->{
		Set<Customer> customers = cserv.getAllCustomers();
		String json = gson.toJson(customers);
		
		String user = ctx.queryParam("username");
		if(user != null) {
			Customer customer = cserv.getCustomerByUsername(user);
			ctx.result(gson.toJson(customer));
		}
		else {
			ctx.result(json);
		}
		ctx.status(200);
	};
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("customer_id");
		Customer customer = cserv.getCustomerById(Integer.parseInt(id));
		if(customer == null) {
			ctx.status(404);
		}
		else {
			String json = gson.toJson(customer);
			ctx.status(200);
			ctx.result(json);
		}
	};
	
	public static Handler createCustomer = (ctx)->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.createNewCustomer(customer);
		ctx.status(201);
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler updateCustomer = (ctx)->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		
		int custId = Integer.parseInt(ctx.pathParam("customer_id"));
		customer.setCustomerId(custId);
		cserv.updateCustomer(customer);
		if(customer.getCustomerId() == 0) {
			ctx.status(404);
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(customer));
		}		
	};
	
	
	public static Handler deleteCustomer = (ctx)->{
		String id = ctx.pathParam("customer_id");
		Boolean result = cserv.deleteCustomer(Integer.parseInt(id));
		
		if(result == true) {
			ctx.status(200);
			ctx.result(result.toString());
		}
		else {
			ctx.status(404);
		}
	};
	
}
