package dev.kurt.controllers;

import java.util.Set;
import com.google.gson.Gson;
import dev.kurt.entities.Customer;
import dev.kurt.services.CustomerService;
import dev.kurt.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	
	public static CustomerService cServ = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	
	public static Handler getAllCustomers = (ctx) ->{
		Set<Customer> customers = cServ.getAllCustomers();				
		String json = gson.toJson(customers);	
		String userQ = ctx.queryParam("username");
		
		if(userQ != null) {
			Customer customer = cServ.getCustomerByUsername(userQ);
			ctx.result(gson.toJson(customer));
		}else {
			ctx.result(json);
		}
	};
	
	public static Handler createCustomer = (ctx) ->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);  
		cServ.createCustomer(customer);
		ctx.status(201); 
		ctx.result(gson.toJson(customer));
	};
	
	
	public static Handler getCustomerById = (ctx) ->{
		String id = ctx.pathParam("cid");
		Customer customer = cServ.getCustomerById(Integer.parseInt(id));	
		String json = gson.toJson(customer);
		ctx.result(json);	
	};
	
	
	
	public static Handler updateCustomer = (ctx) ->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cServ.updateCustomer(customer);	
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler deleteCustomer = (ctx) ->{
		String id = ctx.pathParam("cid");
		Boolean result = cServ.deleteCustomerById(Integer.parseInt(id));
		ctx.result(result.toString());
	};

}
