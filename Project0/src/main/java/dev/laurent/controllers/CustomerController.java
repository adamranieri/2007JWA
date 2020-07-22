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
	
	// will return all schools as a JSON
	public static Handler getAllCustomers = (ctx) ->{
		Set<Customer> customers = cserv.getAllCustomers();				
		String json = gson.toJson(customers);
		ctx.result(json);
		
//		String UT = ctx.queryParam("username");
//		if(UT != null) {
//			Set<Customer> customer = cserv.getCustomerByUsername(UT.toString());
//			ctx.result(gson.toJson(customer));
//		}else {
//			ctx.result(json);	
//		}
	};
	
	// your services should not have to deal with JSONs only your controllers
	public static Handler createCustomer= (ctx) ->{
		//context object ctx is an object that contains the http request and response objects
		// it contains a whole bunch of method for dealing with getting information from the request
		// and sending information in the http response
		
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class); // transform the json into a Shcool object
		cserv.addCustomer(customer);
		ctx.status(201); // 201 is the status code to return if you successfully add something
		
		//usually you want to return the created object
		ctx.result(gson.toJson(customer));
	};
	
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("cId");
		Customer customer = cserv.getCustomerById(Integer.parseInt(id));
		//school object but I want it sent back as a JSON	
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
